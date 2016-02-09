package geostat

import scala.collection.mutable.TreeSet
import scala.math._

/**
 * MapPointSet
 */
class MapPointSet extends TreeSet[MapPoint] {

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
   */
  def radiusQuery(center: MapPoint, radius: Double): Set[MapPoint] = {

    val ptfrom = center.destination(radius, 225.0)
    val ptto = center.destination(radius, 45.0)

    range(ptfrom, ptto).filter { x => center.greatCircleDistance(x) <= radius }.toSet

  }

  /**
   * Get the set of points that away from the point less than radiusE and greater than radiusI
   *
   * @param center center of the search
   * @param radiusI internal radius search
   * @param radiusE external radius search
   * @return set of points
   */
  def radiusQuery(center: MapPoint, radiusI: Double, radiusE: Double): Set[MapPoint] = {

    require(radiusE >= radiusI)

    val ptfrom = center.destination(radiusE, (5.0 * Pi / 4.0).toDegrees)
    val ptto = center.destination(radiusE, (Pi / 4.0).toDegrees)

    range(ptfrom, ptto).map { x => (center.greatCircleDistance(x), x) }
      .filter { x => (x._1 >= radiusI) && (x._1 <= radiusE) }
      .map { x => x._2 }
      .toSet
  
  }
  
  /**
   * Get the set of points that away from the point less than radiusE and greater than radiusI
   *
   * @param center center of the search
   * @param radiusI internal radius search
   * @param radiusE external radius search
   * @param direction angular direction expressed in degrees
   * @param atol angular tolerance expressed in degrees
   * @return set of points
   */
  def radiusQuery(center: MapPoint, radiusI: Double, radiusE: Double, direction: Double, atol: Double): Set[MapPoint] = {

    require(radiusE >= radiusI)

    val ptfrom = center.destination(radiusE, (5.0 * Pi / 4.0).toDegrees)
    val ptto = center.destination(radiusE, (Pi / 4.0).toDegrees)

    range(ptfrom, ptto).map { x => (center.greatCircleDistance(x), x) }
      .filter { x => (x._1 >= radiusI) && (x._1 <= radiusE)}
      .filter{x => (center.bearing(x._2)<=direction+atol) && (center.bearing(x._2)>=direction-atol)}
      .map { x => x._2 }
      .toSet
  
  }
 
  /**
   * This method finds a simple average latitude and longitude for the locations
   * 
   * @return average of the map points set
   */
  def average(): Option[MapPoint] = {

    if (size == 0) return None

    val avg = map(x => x.geodetic2cart).
      foldLeft((0.0, 0.0, 0.0, 0.0))((acc, x) =>
        (acc._1 + x._1, acc._2 + x._2, acc._3 + x._3, acc._4 + x._4))

    Some(new MapPoint((avg._1 / size, avg._2 / size, avg._3 / size, avg._4 / size)))

  }

  //override def toString() = map { x => x.toString }.reduceLeft(_ + _)
  
}
