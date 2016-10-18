package geostat.lattice

import scala.math._
import geostat.MapPoint
import geostat.MapPointSet

/**
 * RandomLattice Lattice
 *
 * @param npts number of points uniformly distrbuted on the surface
 */
class RandomLattice(npts: Int = 1) extends Lattice {

  require(npts > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    for (i <- 0 to npts - 1) {

      val u = random
      val v = random
      
     set.add(new MapPoint((acos(2.0 * v - 1.0)).toDegrees-90.0,
         (2.0 * Pi * u).toDegrees-180.0))

    }

    set

  }

}