package geostat.lattice

import scala.math._
import geostat.MapPoint
import geostat.MapPointSet

/**
 * Fibonacci Lattice
 *
 * @param N number of point in the lattice grid.
 * 	The number of points in the lattice are 2N+1
 */
class FibonacciLattice(N: Int = 1) extends Lattice {

  private final val PHI = (1.0 + sqrt(5.0)) / 2.0

  require(N > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    for (i <- -N to N) {

      var lat = asin((2.0 * i) / (2.0 * N + 1.0)).toDegrees
      var lon = (i.toDouble % PHI) * 360.0 / PHI

      if (lon < - 180.0) lon = 360.0 + lon
      if (lon > 180.0) lon = lon - 360.0

      //println(("%.6f").format(lat) + "," + ("%.6f").format(lon))

      set.add(new MapPoint(lat, lon))

    }

    set

  }

}