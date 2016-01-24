package geostat

import scala.math._
import scala.util.Random
import org.apache.mahout.math._
import scalabindings._
import RLikeOps._

object HelloWorld {

  def main(args: Array[String]): Unit = {
    /*  
    val center = new MapPoint(45.635477f, 8.798650f)
    var points = Set[MapPoint]()

    for (i <- 1 to 1000000) {
      points += new MapPoint(45.635477f + (2.0f * Random.nextFloat() - 1.0f) / 500.0f, 8.798650f + (2.0f * Random.nextFloat() - 1.0f) / 500.0f)
    }

    var tree = new MapPointSet(points)

    println("begin")
    for (i <- 1 to 1000) {
      tree.radiusQuery(center, 30.0f) 
      println(i)
    }
    println("end")
	  println(center)
    println(tree.average())
*/

    val v: Vector = (2.0f, 2.0f, 3.0f)
    val x = v(0)

    println(new MapPoint(45.635477f, 8.798650f))
    println(new MapPoint(45.635477f, 8.798650f).geodetic2cart())
    println(new MapPoint(new MapPoint(45.635477f, 8.798650f).geodetic2cart(), 0.0f))

    println(new MapPoint(45.635477f, 8.798650f).bearing(new MapPoint(45.635477f, 8.798650f + 1)))
    println(new MapPoint(45.635477f, 8.798650f).bearing(new MapPoint(45.635477f, 8.798650f - 1)))
    println(new MapPoint(45.635477f, 8.798650f).bearing(new MapPoint(45.635477f + 1, 8.798650f + 1)))
    println(new MapPoint(45.635477f, 8.798650f).bearing(new MapPoint(45.635477f - 1, 8.798650f - 1)))

    println()
    println(new MapPoint(45.635477f, 8.798650f))
    println(new MapPoint(45.635477f + 1.0f, 8.798650f + 1.0f))
    println(new MapPoint(45.635477f, 8.798650f).midpoint(new MapPoint(45.635477f + 1.0f, 8.798650f + 1.0f)))

  }

}
