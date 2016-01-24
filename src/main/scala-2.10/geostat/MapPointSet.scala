package geostat

import scala.collection.mutable.TreeSet
import scala.math._
import org.apache.mahout.math._
import scalabindings._
import RLikeOps._
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
   * */
  def radiusQuery(center: MapPoint, radius: Float): Set[MapPoint] = {

    val ptfrom = center.destination(radius, (5.0f * Pi.toFloat / 4.0f))
    val ptto = center.destination(radius, (Pi.toFloat / 4.0f))

    range(ptfrom, ptto).filter { x => center.greatCircleDistance(x) < radius }.toSet

  }
  
  /**
   * This method finds a simple average latitude and longitude for the locations
   * 
   * */
  def average () : MapPoint = {
    
    var zero : Vector = (0.0f, 0.0f, 0.0f)
    //var avg_val = 0.0f
 
    /*
    for (x <- this) {
      avg += x.geodetic2cart()
      avg_val += x.value
    }
    */
    val avg : Vector = foldLeft(zero)(_ + _.geodetic2cart()) / size
    val avg_val = foldLeft(0.0f)(_ + _.value) / size
    new MapPoint(avg, avg_val)
  
  }

}