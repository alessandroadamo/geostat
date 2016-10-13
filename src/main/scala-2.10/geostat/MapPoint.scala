package geostat

import scala.math._

object MapPoint extends WGS84 {

  val MIN_LATITUDE = -90.0
  val MAX_LATITUDE = +90.0
  val MIN_LONGITUDE = -180
  val MAX_LONGITUDE = +180

  private val MIN_DISTANCE = 1e-1

  /**
   * Convert latitude/longitude coordinate to 3D Cartesian coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate
   */
  def geodetic2cart(pt: MapPoint): (Double, Double, Double, Double) = {

    val lat = pt.latitude
    val lon = pt.longitude

    val slat = sin(lat)
    val clat = cos(lat)
    val slong = sin(lon)
    val clong = cos(lon)

    (R * clat * clong, R * clat * slong, R * slat, pt.value)

  }

  /**
   * Convert 3D Cartesian coordinate to latitude/longitude coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate
   */
  def cart2geodetic(cart: (Double, Double, Double, Double)) = new MapPoint(asin(cart._3 / R).toDegrees.toFloat, atan2(cart._2, cart._1).toDegrees, cart._4)

  private def latitude2int(latitude: Double) = floor((latitude - MapPoint.MIN_LATITUDE) * 1e6f).toInt

  private def longitude2int(longitude: Double) = floor((longitude - MapPoint.MIN_LONGITUDE) * 1e6f).toInt

  private def zorder(a: Int, b: Int): Long = {

    var x: Long = a & 0x7fffffffL
    x = (x ^ (x << 32)) & 0x00000000ffffffffL
    x = (x ^ (x << 16)) & 0x0000ffff0000ffffL
    x = (x ^ (x << 8)) & 0x00ff00ff00ff00ffL
    x = (x ^ (x << 4)) & 0x0f0f0f0f0f0f0f0fL
    x = (x ^ (x << 2)) & 0x3333333333333333L
    x = (x ^ (x << 1)) & 0x5555555555555555L

    var y: Long = b & 0x7fffffffL
    y = (y ^ (y << 32)) & 0x00000000ffffffffL
    y = (y ^ (y << 16)) & 0x0000ffff0000ffffL
    y = (y ^ (y << 8)) & 0x00ff00ff00ff00ffL
    y = (y ^ (y << 4)) & 0x0f0f0f0f0f0f0f0fL
    y = (y ^ (y << 2)) & 0x3333333333333333L
    y = (y ^ (y << 1)) & 0x5555555555555555L

    x | (y << 1)

  }

}

/**
 * MapPoint class
 *
 * @param latitude latitude
 * @param longitude longitude
 * @param value value
 */
@SerialVersionUID(123L)
class MapPoint(val latitude: Double = 0.0, val longitude: Double = 0.0, var value: Double = Double.NaN)
    extends WGS84 with Serializable with Ordered[MapPoint] {

  import MapPoint._

  require(latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE)
  require(longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE)

  val key: Long = zorder(latitude2int(latitude), longitude2int(longitude)) // Z-order key

  private def hav(theta: Double) = { val h = sin(0.5 * theta); h * h }

  /**
   * MapPoint constructor from a tuple
   *
   * @param cart 3D Cartesian coordinate and value tuple
   */
  def this(cart: (Double, Double, Double, Double)) = this(asin(cart._3 / 6371008.77141).toDegrees, atan2(cart._2, cart._1).toDegrees, cart._4);

  /**
   * Haversine distance
   *
   * @param pt point
   * @return return the distance between the point p and the object point
   */
  def haversineDistance(pt: MapPoint) = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians
    val lat2 = pt.latitude.toRadians
    val lon2 = pt.longitude.toRadians

    val dlat = lat2 - lat1
    val dlong = lon2 - lon1

    2.0 * R * sqrt(hav(dlat) + cos(lat1) * cos(lat2) * hav(dlong))

  }

  /**
   * Great-circle distance
   *
   * @param pt point
   * @return return the distance between the point p and the object point
   */
  def greatCircleDistance(pt: MapPoint) = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians
    val lat2 = pt.latitude.toRadians
    val lon2 = pt.longitude.toRadians

    val dlon = lon2 - lon1

    R * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(dlon))

  }

  /**
   * Given a start point and a distance d along a constant bearing, this will calculate the destination point.
   *
   * @param distance distance expressed in meters
   * @param bearing bearing expressed in degrees
   */
  def destination(distance: Double, bearing: Double): MapPoint = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians
    val brng = bearing.toRadians

    val lat2 = asin(sin(lat1) * cos(distance / R) + cos(lat1) * math.sin(distance / R) * cos(brng))
    val lon2 = lon1 + atan2(sin(brng) * sin(distance / R) * cos(lat1),
      cos(distance / R) - sin(lat1) * sin(lat2))
    return new MapPoint(lat2.toDegrees, lon2.toDegrees)

  }

  /**
   * This method calculate initial bearing which if followed in a straight line
   * along a great-circle arc will take you from the start point to the end point.
   *
   * @param pt end point
   * @return bearing
   */
  def bearing(pt: MapPoint) = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians
    val lat2 = pt.latitude.toRadians
    val lon2 = pt.longitude.toRadians

    val clat2 = cos(lat2)
    val dlon = lon2 - lon1

    val y = sin(dlon) * cos(lat2)
    val x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(dlon)

    val ang = atan2(y, x).toDegrees
    if (ang < 0.0) 360.0 + ang else ang

  }

  /**
   * Convert latitude/longitude coordinate to 3D Cartesian coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate and value
   */
  def geodetic2cart(): (Double, Double, Double, Double) = {

    val lat = latitude.toRadians
    val lon = longitude.toRadians

    val slat = sin(lat)
    val clat = cos(lat)
    val slong = sin(lon)
    val clong = cos(lon)

    (R * clat * clong, R * clat * slong, R * slat, value)

  }

  /**
   * Calculate the half-way point along a great circle path between the two points
   *
   * @param pt point
   * @return midpoint
   */
  def midpoint(pt: MapPoint): MapPoint = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians
    val lat2 = pt.latitude.toRadians
    val lon2 = pt.longitude.toRadians

    val dlon = lon2 - lon1

    val Bx = cos(lat2) * cos(dlon)
    val By = cos(lat2) * sin(dlon)

    val lat3 = atan2(sin(lat1) + sin(lat2), sqrt((cos(lat1) + Bx) * (cos(lat1) + Bx) + By * By)).toFloat
    var lon3 = lon1 + atan2(By, cos(lat1) + Bx)
    lon3 = ((lon3 + 3.0f * Pi) % (2.0f * Pi) - Pi) // normalise to -180..+180°

    new MapPoint(lat3.toDegrees, lon3.toDegrees, 0.5 * (this.value + pt.value))

  }

  /**
   * Cross Track Distance compute the distance from the point pt and the segment passing throw
   * this point and the ptdest point
   *
   * @param ptdest destination point
   * @param pt third point
   * @return cross track distance
   */
  def crossTrackDistence(ptdest: MapPoint, pt: MapPoint) =
    asin(sin(greatCircleDistance(pt) * sin(bearing(pt) - bearing(ptdest))))

  /**
   * Along Track Distances is the distance from the start point (this) to the closest
   * point on the path to a third point pt, following a great circle path defined by this point and ptdest.
   * MIN_DISTANCE
   * @param ptdest destination point
   * @param pt third point
   * @return along track distance
   */
  def alongTrackDistance(ptdest: MapPoint, pt: MapPoint) = {
    val distAD = greatCircleDistance(pt)
    val xtd = asin(sin(greatCircleDistance(pt) * sin(bearing(pt) - bearing(ptdest))))
    acos(cos(distAD) / cos(xtd))
  }

  //  def compare(that: MapPoint) = if (this.greatCircleDistance(that) < MIN_DISTANCE) 0 else this.key.compare(that.key) // less than 0.5m
  def compare(that: MapPoint) = this.key.compare(that.key)
  
  override def toString() = {

    val builder = StringBuilder.newBuilder

    builder.append("{\"type\":\"Feature\",\"_id\":")
    builder.append(key)
    builder.append(",\"geometry\":{\"type\":\"Point\",\"coordinates\":[")
    builder.append(longitude)
    builder.append(",")
    builder.append(latitude)
    builder.append("]},\"properties\":{\"value\":")
    if (!value.isNaN())
      builder.append(value)
    else builder.append("null")
    builder.append("}}\n")

    builder.toString()

  }

}
