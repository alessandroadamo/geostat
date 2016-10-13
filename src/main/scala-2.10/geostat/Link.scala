package geostat

import scala.math._

/**
 * Link abstract class
 *
 * @param nodeA first node
 * @param nodeB second node
 */
@SerialVersionUID(123L)
abstract class Link(val nodeA: MapPoint, val nodeB: MapPoint) extends Serializable {

  require(nodeA != null)
  require(nodeB != null)

  val lenght = nodeA.greatCircleDistance(nodeB) // length of the link

  /**
   * Calculate the half-way point along a great circle path between the nodeA and nodeB points
   *
   * @return midpoint
   */
  def midpoint(): MapPoint = {

    val lat1 = nodeA.latitude.toRadians
    val lon1 = nodeA.longitude.toRadians
    val lat2 = nodeB.latitude.toRadians
    val lon2 = nodeB.longitude.toRadians

    val dlon = lon2 - lon1

    val Bx = cos(lat2) * cos(dlon)
    val By = cos(lat2) * sin(dlon)

    val lat3 = atan2(sin(lat1) + sin(lat2), sqrt((cos(lat1) + Bx) * (cos(lat1) + Bx) + By * By)).toFloat
    var lon3 = lon1 + atan2(By, cos(lat1) + Bx)
    lon3 = ((lon3 + 3.0f * Pi) % (2.0f * Pi) - Pi) // normalise to -180..+180°

    new MapPoint(lat3.toDegrees, lon3.toDegrees, 0.5 * (nodeA.value + nodeA.value))

  }

  override def toString() = {
  
    val builder = StringBuilder.newBuilder

    builder.append("{\"nodes\":[")
    builder.append(nodeA.key)
    builder.append(",")
    builder.append(nodeB.key)
    builder.append("]}\n")

    builder.toString()
  
  }

}