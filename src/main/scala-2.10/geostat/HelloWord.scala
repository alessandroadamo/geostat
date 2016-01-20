package geostat

import scala.collection.mutable.TreeSet
import scala.math._
import scala.util.Random

object HelloWorld {

  def main(args: Array[String]): Unit = {

    val center = new MapPoint(45.635477f, 8.798650f)
    /*
    val pt2 = new MapPoint[Unit](45.635184f, 8.798854f)
    val pt3 = new MapPoint[Unit](45.635735f, 8.798816f)

    val pt4 = new MapPoint[Unit](45.636249f, 8.798140f)
    val pt5 = new MapPoint[Unit](45.636380f, 8.798950f)
    val pt6 = new MapPoint[Unit](45.634178f, 8.799192f)
    val pt7 = new MapPoint[Unit](45.633619f, 8.798913f)
	*/

    // val mps = new MapPointSet[Unit](Set(pt1, pt2, pt3, pt4, pt5, pt6, pt7))
    //println(pt6.greatCircleDistance(pt7))
    //println(pt6.haversineDistance(pt7))
    // mps.foreach { x => println(x) }

    // val tree = TreeSet[MapPoint[Unit]](pt1, pt2, pt3, pt4, pt5, pt6, pt7)
    // println(tree)

    // println(pt2.key)
    // println(pt4.key)
    // println(tree.range(pt2,pt4))

    /*
    val ptA = center.destination(50.0f, 0.0f)
    val ptB = center.destination(50.0f, (Pi.toFloat / 2.0f))
    val ptC = center.destination(50.0f, Pi.toFloat)
    val ptD = center.destination(50.0f, (3.0f * Pi.toFloat / 2.0f))
		*/
    
    /*
     val ptA = center.destination(50.0f, (Pi.toFloat / 4.0f))
    val ptB = center.destination(50.0f, (3.0f * Pi.toFloat / 4.0f))
    val ptC = center.destination(50.0f, (5.0f * Pi.toFloat / 4.0f))
    val ptD = center.destination(50.0f, (7.0f * Pi.toFloat / 7.0f))
	 
    println (ptA.key)
    println (ptB.key)
    println (ptC.key)
    println (ptD.key) 
    */
    
    var points = Set[MapPoint]()

    for (i <- 1 to 1000) {
      points += new MapPoint(45.635477f + (2.0f*Random.nextFloat()-1.0f)/500.0f, 8.798650f + (2.0f*Random.nextFloat()-1.0f)/500.0f)
    }
    
    var tree = new MapPointSet(points)
    
    tree.radiusQuery(center, 30.0f) foreach println
    
   // println
   // tree foreach println
   

  }

}
