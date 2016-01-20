package geostat

import scala.collection.mutable.TreeSet
import scala.math._

/**
 * MapPointSet
 */
class MapPointSet() extends TreeSet[MapPoint] {

  def this(points: Set[MapPoint]) {

    this()
    this ++= points

  }

  /**
   * Get the set of points that away from the point less than radius
   * 
   * @param center center of the search
   * @param radius radius search
   * @return set of points 
   * */
  def radiusQuery(center: MapPoint, radius: Float): Set[MapPoint] = {

    val ptfrom = center.destination(radius, (5.0f * Pi.toFloat / 4.0f))
    val ptto = center.destination(radius, (Pi.toFloat / 4.0f))

    range(ptfrom, ptto).filter { x => center.greatCircleDistance(x) < radius }.toSet

  }
  
}