package geostat

import geostat.lattice.Lattice


/**
 * Triangle Lattice
 * 
 * @param origin origin of the lattice
 * @param dist distance between points
 * @param nlat number of latitude segments
 * @param nlon number of longitude segments
 */
class TriangleLattice(origin: MapPoint, dist: Double = 0.0, nlat: Int = 1, nlon: Int = 1) extends Lattice {

  require(dist >= 0.0)
  require(nlat > 0)
  require(nlon > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    var pt: MapPoint = origin
    for (i <- 0 to nlon-1) {
     
      for (j <- 0 to nlat-1) set.add(pt.destination(dist * j, 0))
      
      var ptold = pt
      if (i % 2 == 0)
        pt = ptold.destination(dist, 60.0)
      else
        pt = ptold.destination(dist, 120.0)
   
    }
    set
    
  }

}