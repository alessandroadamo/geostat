package geostat

/**
 * Cartesian Grid
 * 
 * @param origin origin of the grid
 * @param distLat distance between points on latitude axes
 * @param distLon distance between points on longitude axes
 * @param nlat number of latitude segments
 * @param nlon number of longitude segments
 */
class CartesianGrid(origin: MapPoint, distLat: Double = 0.0, distLon: Double = 0.0, nlat: Int = 1, nlon: Int = 1) extends Grid {

  require(distLat >= 0.0)
  require(distLon >= 0.0)
  require(nlat > 0)
  require(nlon > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    var pt: MapPoint = origin
    for (i <- 0 to nlon-1) {
     
      for (j <- 0 to nlat-1) set.add(pt.destination(distLat * j, 0))
      
      var ptold = pt
      pt = ptold.destination(distLon, 90.0)
     
    }
    set
    
  }

}