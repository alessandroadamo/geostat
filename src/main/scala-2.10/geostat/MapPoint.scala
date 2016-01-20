package geostat

import scala.math._

object MapPoint {

  val MIN_LATITUDE = -90.0f
  val MAX_LATITUDE = +90.0f
  val MIN_LONGITUDE = -180f
  val MAX_LONGITUDE = +180f

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
 */
@SerialVersionUID(123L)
class MapPoint(val latitude: Float, val longitude: Float) extends WGS84 with Serializable with Ordered[MapPoint] {

  import MapPoint._

  require(latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE)
  require(longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE)

  private def hav(theta: Float) = { val h = sin(0.5f * theta); h * h }

  val key: Long = zorder(latidure2int(latitude), longitude2int(longitude)) // Z-order key

  /**
   * Haversine distance
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

    2.0f * R * sqrt(hav(dlat) + cos(lat1) * cos(lat2) * hav(dlong))

  }

  /**
   * Great-circle distance
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
   */
  def destination(distance: Float, bearing: Float): MapPoint = {

    val lat1 = latitude.toRadians.toDouble
    val lon1 = longitude.toRadians.toDouble

    val lat2 = asin(sin(lat1) * cos(distance / R) + cos(lat1) * sin(distance / R) * cos(bearing))
    val lon2 = lon1 + atan2(sin(bearing) * sin(distance / R) * cos(lat1), cos(distance / R) - sin(lat1) * sin(lat2))

    return new MapPoint(lat2.toDegrees.toFloat, lon2.toDegrees.toFloat)

  }

  def compare(that: MapPoint): Int = this.key.compare(that.key)

//  override def toString() = "key: " + key + " ( " + latitude + ", " + longitude + " )\n"
  override def toString() = latitude + ", " + longitude

}
