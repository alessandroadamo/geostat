package geostat

import org.apache.mahout.math._
import scalabindings._
import RLikeOps._
import scala.math._

object MapPoint extends WGS84 {

  val MIN_LATITUDE = -90.0
  val MAX_LATITUDE = +90.0
  val MIN_LONGITUDE = -180
  val MAX_LONGITUDE = +180

  /**
   * Convert latitude/longitude coordinate to 3D Cartesian coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate
   */
  def geodetic2cart(latitude: Float, longitude: Float): Vector = {

    require(latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE)
    require(longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE)

    val lat = latitude.toRadians
    val lon = longitude.toRadians

    val slat = sin(lat)
    val clat = cos(lat)
    val slong = sin(lon)
    val clong = cos(lon)

    (R * clat * clong, R * clat * slong, R * slat)

  }

  /**
   * Convert 3D Cartesian coordinate to latitude/longitude coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate
   */
  def cart2geodetic(cart: Vector) = new MapPoint(asin(cart(2) / R).toDegrees.toFloat, atan2(cart(1), cart(0)).toDegrees.toFloat)

  private def latidure2int(latitude: Float) = floor((latitude - MapPoint.MIN_LATITUDE) * 1e6f).toInt

  private def longitude2int(longitude: Float) = floor((longitude - MapPoint.MIN_LONGITUDE) * 1e6f).toInt

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
 * @param value value object
 * @param weight weight value
 */
@SerialVersionUID(123L)
class MapPoint(val latitude: Float, val longitude: Float, var value: Float = 0.0f)
    extends WGS84 with Serializable with Ordered[MapPoint] {

  import MapPoint._

  require(latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE)
  require(longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE)

  val key: Long = zorder(latidure2int(latitude), longitude2int(longitude)) // Z-order key

  /**
   * Create a new MapPoint from a 3D Cartesian point
   *
   * @param cart 3D Cartesian point
   * @param value value
   */
  def this(cart: Vector, value: Float) = this(asin(cart(2) / MapPoint.R).toDegrees.toFloat,
    atan2(cart(1), cart(0)).toDegrees.toFloat, value)

  private def hav(theta: Float) = { val h = sin(0.5 * theta); h * h }

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
   * @param distance
   * @param bearing
   */
  def destination(distance: Float, bearing: Float): MapPoint = {

    val lat1 = latitude.toRadians
    val lon1 = longitude.toRadians

    val lat2 = asin(sin(lat1) * cos(distance / R) + cos(lat1) * sin(distance / R) * cos(bearing))
    val lon2 = lon1 + atan2(sin(bearing) * sin(distance / R) * cos(lat1), cos(distance / R) - sin(lat1) * sin(lat2))

    return new MapPoint(lat2.toDegrees.toFloat, lon2.toDegrees.toFloat)

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
    atan2(y, x).toDegrees

  }

  /**
   * Convert latitude/longitude coordinate to 3D Cartesian coordinate
   *
   * @param latitude latitude
   * @param longitude longitude
   * @return the 3D Cartesian coordinate
   */
  def geodetic2cart(): Vector = {

    val lat = latitude.toRadians
    val lon = longitude.toRadians

    val slat = sin(lat)
    val clat = cos(lat)
    val slong = sin(lon)
    val clong = cos(lon)

    (R * clat * clong, R * clat * slong, R * slat)

  }

  /**
   * Caculate the half-way point along a great circle path between the two points
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

    val lat3  = atan2(sin(lat1) + sin(lat2), sqrt((cos(lat1) + Bx) * (cos(lat1) + Bx) + By * By)).toFloat
    var lon3 = lon1 + atan2(By, cos(lat1) + Bx)
    lon3  = ((lon3 + 3.0f * Pi) % (2.0f * Pi) - Pi) // normalise to -180..+180°
    
    new MapPoint(lat3.toDegrees, lon3.toFloat.toDegrees, 0.5f * ( this.value + pt.value ))

  }

  def compare(that: MapPoint) = this.key.compare(that.key)

  //  override def toString() = "key: " + key + " ( " + latitude + ", " + longitude + " )\n"
  override def toString() = latitude + ", " + longitude

}
