package geostat

import scala.math._
import scala.util.Random
import geostat.kriging.model._
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import geostat.lattice.LatLonLattice
import geostat.lattice.FibonacciLattice
import geostat.lattice.RandomLattice

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

    //  var grid = new LatLongLattice(7.0, 7.0)
    //  println(grid.vertex.toGeoJSON)

    /*var pt1 = new MapPoint(90.0-1e-4, 0.0)
    */
    //var pt2 = new MapPoint(90.0-1e-4, 1e-6)

    //println(pt2)
    // print(pt1.equals(pt2+new MapPoint(0.01,0.0)))
    /*
    var grid = new RandomLattice(100)
    //println(grid.vertex.toGeoJSON)

    val f = new Face(new MapPoint(45.489421, 9.120813),
        new MapPoint(45.491732, 9.134172),
        new MapPoint(45.474157, 9.154149))
    println(f)
  
*/

    var phi = (1.0 + sqrt(5.0)) / 2.0
    val nn = sqrt(pow(phi, 2.0) + 1.0)
    phi /= nn
    var one = 1.0 / nn

    val a = MapPoint.cart2geodesic(0.0, MapPoint.R * one, MapPoint.R * phi, Double.NaN)
    //  println(a)
    val b = MapPoint.cart2geodesic(0.0, MapPoint.R * one, -MapPoint.R * phi, Double.NaN)
    //  println(b)
    val c = MapPoint.cart2geodesic(0.0, -MapPoint.R * one, MapPoint.R * phi, Double.NaN)
    //  println(c)
    val d = MapPoint.cart2geodesic(0.0, -MapPoint.R * one, -MapPoint.R * phi, Double.NaN)
    //  println(d)
    val e = MapPoint.cart2geodesic(MapPoint.R * one, MapPoint.R * phi, 0.0, Double.NaN)
    //  println(e)
    val f = MapPoint.cart2geodesic(MapPoint.R * one, -MapPoint.R * phi, 0.0, Double.NaN)
    //  println(f)
    val g = MapPoint.cart2geodesic(-MapPoint.R * one, MapPoint.R * phi, 0.0, Double.NaN)
    //  println(g)
    val h = MapPoint.cart2geodesic(-MapPoint.R * one, -MapPoint.R * phi, 0.0, Double.NaN)
    //  println(h)
    val i = MapPoint.cart2geodesic(MapPoint.R * phi, 0.0, MapPoint.R * one, Double.NaN)
    //  println(i)
    val l = MapPoint.cart2geodesic(-MapPoint.R * phi, 0.0, MapPoint.R * one, Double.NaN)
    //  println(l)
    val m = MapPoint.cart2geodesic(MapPoint.R * phi, 0.0, -MapPoint.R * one, Double.NaN)
    //  println(l)
    val n = MapPoint.cart2geodesic(-MapPoint.R * phi, 0.0, -MapPoint.R * one, Double.NaN)
    //  println(n)
    //  println()

    val aa = new Face(e, g, a)

    val bb = aa.split()
    println(bb)
    println()

    /*
    for (e <- bb) {
      //   println(e.nodeA.haversineDistance(e.nodeB))
      //   println(e.nodeA.haversineDistance(e.nodeC))
      //   println(e.nodeB.haversineDistance(e.nodeC))
      println(e.nodeA.greatCircleDistance(e.nodeB))
      println(e.nodeA.greatCircleDistance(e.nodeC))
      println(e.nodeB.greatCircleDistance(e.nodeC))
      println()
    }*/

    // val aaa = e.midpoint(g)
    // println(e.greatCircleDistance(aaa))
    // println(g.greatCircleDistance(aaa))
    // println()

    /*
    var set = new MapPointSet()
    set.add(a)
    set.add(b)
    set.add(c)
    set.add(d)
    set.add(e)
    set.add(f)
    set.add(g)
    set.add(h)
    set.add(i)
    set.add(l)
    set.add(m)
    set.add(n)

    println(set)

    val aa = new Face(e, g, a)
    val bb = new Face(e, g, b)
    val cc = new Face(g, l, g)
    val dd = new Face(g, l, h)
    val ee = new Face(c, e, g)
    // val ff = new Face(c, e, l)
    // val gg = new Face(d, f, m)
    // val hh = new Face(d, f, n)

    var mesh = new MeshSet()
    mesh.add(aa)
    mesh.add(bb)
    mesh.add(cc)
    mesh.add(dd)
    // mesh.add(ee)

    println(mesh)
    */

  }

}