package geostat.lattice

import geostat.MapPoint
import geostat.MapPointSet

/**
 * Lattice Lattice
 *
 * @param origin origin of the grid
 * @param dist distance between points
 * @param nlat number of latitude segments
 * @param nlon number of longitude segments
 */
class HexagonLattice(origin: MapPoint, dist: Double = 0.0, nlat: Int = 1, nlon: Int = 1) extends Lattice {

  require(dist >= 0.0)
  require(nlat > 0)
  require(nlon > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    var pt: MapPoint = origin
    for (i <- 0 to nlon - 1) {

      for (j <- 0 to nlat - 1) set.add(pt.destination(dist * j, 0))

      /**
       * TODO
       */

    }
    set

  }

}