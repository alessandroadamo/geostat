package geostat

import scala.math._
import scala.util.Random
import geostat.kriging.model._
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter

object Main {

  def main(args: Array[String]): Unit = {

    val center = new MapPoint(45.635477, 8.798650)
    var points = Set[MapPoint]()

    for (i <- 1 to 100000) {
      points += new MapPoint(45.635477 + (2.0 * Random.nextDouble() - 1.0) / 100.0,
        8.798650 + (2.0f * Random.nextDouble() - 1.0) / 100.0)
    }

    var tree = new MapPointSet(points)
    
    //println(center)
    //println(tree.average)

    val tr = tree.radiusQuery(center, 250.0, 1000.0, 90.0, 10.0)

    //for (t  <-  tree) println(t)

     for (t  <-  tr) println(t)

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

    /*  val center = new MapPoint(45.635477, 8.798650)
    val from = center.destination(1000, 225.0)
    val to = center.destination(1000, 25.0)
    
    // var grid = new GeodesicGrid(from, 10.0, 100, 100)
    var grid = new CartesianGrid(from, 30.0, 10.0, 30, 5)
    
    for (v <- grid.vertex)  println(v)
    */
 
/*   val c = 4.0
    val a = 0.4
    val cub = new CubicModel(c, a)
    val exp = new ExponentialModel(c, a)
    val gau = new GaussianModel(c, a)
    val nug = new NuggetModel(c)
    val pen = new PentaSphericalModel(c, a)
    val pow = new PowerModel(c, a)
    val sine = new SineHoleEffectModel(c, a)
    val sph = new SphericalModel(c, a)
    val nested = new NestedModel(sine, pen)

    val file = new File("/home/user/Desktop/vario.csv")
    val bw = new BufferedWriter(new FileWriter(file))
    val dx = 0.001
    
    bw.write("cub; exp; gau; nug; pen; pow; sine; sph; nested")
    bw.newLine()
    for (i <- 1 to 10000) {

      val h = i * dx
      bw.write(cub.covariogram(h) + "; " +
        exp.covariogram(h) + "; " +
        gau.covariogram(h) + "; " +
        nug.covariogram(h) + "; " +
        pen.covariogram(h) + "; " +
        pow.covariogram(h) + "; " +
        sine.covariogram(h) + "; " +
        sph.covariogram(h) + "; " +
        nested.covariogram(h))
      bw.newLine()

    }
    bw.close()
*/    
  }

}
