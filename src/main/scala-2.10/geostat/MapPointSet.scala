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
   * @param direction angular direction expressed in degrees. Must be greater than 0.0 and less than 360.0 degrees.
   * @param atol angular tolerance expressed in degrees. Must be greater than 0.0 and less than 180.0 degrees.
   * @return set of points
   */
  def radiusQuery(center: MapPoint, radiusI: Double, radiusE: Double, direction: Double, atol: Double): Set[MapPoint] = {

    require(radiusE >= radiusI)
    require(direction >= 0.0 && direction <= 360.0)
    require(atol >= 0.0 && atol <= 180.0)

    val ptfrom = center.destination(radiusE, (5.0 * Pi / 4.0).toDegrees)
    val ptto = center.destination(radiusE, (Pi / 4.0).toDegrees)

    val minAng = if (direction - atol < 0.0) direction - atol + 360.0 else direction - atol
    val maxAng = if (direction + atol > 360.0) direction + atol - 360.0 else direction + atol

    range(ptfrom, ptto).map { x => (center.greatCircleDistance(x), x) }
      .filter { x => (x._1 >= radiusI) && (x._1 <= radiusE) }
      .map { x => (x._1, x._2, center.bearing(x._2)) }
      .filter { x =>
        if (minAng > maxAng) !((x._3 > maxAng) && (x._3 < minAng))
        else (x._3 <= maxAng) && (x._3 >= minAng)
      }
      .map { x => x._2 }
      .toSet

  }

  /**
   * Finds the mean latitude and longitude for the locations
   *
   * @return mean of the map points set
   */
  def mean(): Option[MapPoint] = {

    if (size == 0) return None

    val avg = map(x => x.geodetic2cart).
      foldLeft((0.0, 0.0, 0.0, 0.0))((acc, x) =>
        (acc._1 + x._1, acc._2 + x._2, acc._3 + x._3, acc._4 + x._4))

    Some(new MapPoint((avg._1 / size, avg._2 / size, avg._3 / size, avg._4 / size)))

  }

  /**
   * Find the standard deviations on latitude and longitude directions for the locations
   * expressed in meters
   *
   * @return standard deviation tuple for latitude and longitude
   */
  def stddev(): (Double, Double) = {

    var stdlat = 0.0
    var stdlon = 0.0

    if (this.size > 1) {

      val distsLat = this.map(pt => pt.greatCircleDistance(new MapPoint(pt.latitude, 0.0)))
      val meanLat = distsLat.sum / distsLat.size.toDouble
      val sumOfSquaresLat = distsLat.foldLeft(0.0)((total, lat) => {
        val square = math.pow(lat - meanLat, 2.0)
        total + square
      })
      stdlat = sqrt(sumOfSquaresLat / (distsLat.size.toDouble - 1.0))

      val distsLon = this.map(pt => pt.greatCircleDistance(new MapPoint(0.0, pt.longitude)))
      val meanLon = distsLon.sum / distsLon.size.toDouble
      val sumOfSquaresLon = distsLon.foldLeft(0.0)((total, lon) => {
        val square = math.pow(lon - meanLon, 2.0)
        total + square
      })
      stdlon = sqrt(sumOfSquaresLon / (distsLon.size.toDouble - 1.0))

    }

    (stdlat, stdlon)

  }

  /**
   * Return a string representing the GeoJSON map point set representation
   *
   * @return the GeoJSON string
   */
  override def toString(): String = {

    val builder = StringBuilder.newBuilder

    var it = this.iterator

    while (it.hasNext)
      builder.append(it.next())

    builder.toString

  }

}
