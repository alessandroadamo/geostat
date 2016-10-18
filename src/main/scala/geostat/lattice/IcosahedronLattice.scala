package geostat

import geostat.lattice.Lattice

/**
 * Icosahedron Lattice
 *
 * @param nlevels number of recursion levels
 */
class TriangleLattice(nlevels: Int = 1) extends Lattice {

  require(nlevels >= 1)

  val vertex = generateVertexSet()

  private def generateVertexSet(): MapPointSet = {

    // TODO
    null

  }

}