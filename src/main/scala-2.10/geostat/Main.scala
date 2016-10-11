package geostat

import scala.math._
import scala.util.Random
import geostat.kriging.model._
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import geostat.lattice.LatLongLattice
import geostat.lattice.FibonacciLattice

object Main {

  def main(args: Array[String]): Unit = {

    // var points = Set[MapPoint]()

    /*
      for (i <- 1 to 2000) {
     
      points += new MapPoint(45.635477 + (2.0 * Random.nextDouble() - 1.0) / 500.0,
        8.798650 + (2.0f * Random.nextDouble() - 1.0) / 500.0)
    }

    var tree = new MapPointSet(points)
    
    //println(center)

    val tr : MapPointSet = new MapPointSet(tree.radiusQuery(center, 0.0, 150.0))

    //val stddev = KernelDensity.silvermanRule(tr)
 
    //val kde = new KernelDensity(stddev._1, stddev._2, tr)
    println(tr)

  //  println(tr.mean)
  //  println(tr.stddev)
  //  println(KernelDensity.silvermanRule(tr))
    
    
    //for (t  <-  tr) println(t)

    // println(tree.average())

    //   val v: Vector = (2.0f, 2.0f, 3.0f)
    //   val x = v(0)
*/

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

    var grid = new LatLongLattice(7.0, 7.0)
    println(grid.vertex.toGeoJSON)

    //  var grid = new FibonacciLattice(100)
    //  println(grid.vertex.toGeoJSON)
    //for (v <- grid.vertex) println(v)


  }

}
