package geostat

import scala.math._
import scala.util.Random

object Main {

  def main(args: Array[String]): Unit = {

 /*   val center = new MapPoint(45.635477, 8.798650)
    var points = Set[MapPoint]()

    for (i <- 1 to 100000) {
      points += new MapPoint(45.635477 + (2.0 * Random.nextDouble() - 1.0) / 100.0,
        8.798650 + (2.0f * Random.nextDouble() - 1.0) / 100.0)
    }

    var tree = new MapPointSet(points)

    
    println(center)
    println(tree.average)
*/    
    // val tr = tree.radiusQuery(center, 100.0, 200.0)
    
    
    // for (t  <-  tree) println(t)

    // for (t  <-  tr) println(t)

    
    
    // println(tree.average())

    //   val v: Vector = (2.0f, 2.0f, 3.0f)
    //   val x = v(0)

    /*
    println(new MapPoint(45.635477, 8.798650))
    println(new MapPoint(45.635477, 8.798650).geodetic2cart())
    println(new MapPoint(new MapPoint(45.635477, 8.798650).geodetic2cart()))

    println(new MapPoint(45.635477, 8.798650f).bearing(new MapPoint(45.635477f, 8.798650f + 1)))
    println(new MapPoint(45.635477, 8.798650f).bearing(new MapPoint(45.635477f, 8.798650f - 1)))
    println(new MapPoint(45.635477, 8.798650f).bearing(new MapPoint(45.635477f + 1, 8.798650f + 1)))
    println(new MapPoint(45.635477, 8.798650f).bearing(new MapPoint(45.635477f - 1, 8.798650f - 1)))

    println()
    println(new MapPoint(45.635477, 8.798650))
    println(new MapPoint(45.635477 + 1.0, 8.798650 + 1.0))
    println(new MapPoint(45.635477, 8.798650)
      .midpoint(new MapPoint(45.635477 + 1.0, 8.798650 + 1.0)))
    */
  
    val center = new MapPoint(45.635477, 8.798650)
    val from = center.destination(1000, 225.0)
    val to = center.destination(1000, 25.0)
    
    var grid = new GeodesicGrid(from, 20.0, 10, 5)
    for (v <- grid.vertex)  println(v)
     
    
  }

}
