package geostat

/**
 * Geodesic Grid
 * 
 * @param origin origin of the grid
 * @param distance distance between points
 * @param nlat number of latitude segments
 * @param nlon number of longitude segments
 */
class GeodesicGrid(origin: MapPoint, distance: Double = 0.0, nlat: Int, nlon: Int) extends Grid {

  require(distance > 0.0)
  require(nlat > 0)
  require(nlon > 0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet

    var pt: MapPoint = origin
    for (i <- 0 to nlat-1) {
     
      for (j <- 0 to nlon-1) set.add(pt.destination(distance * j, 0))
      
      var ptold = pt
      if (i % 2 == 0)
        pt = ptold.destination(distance, 60.0)
      else
        pt = ptold.destination(distance, 120.0)
   
    }
    set
    
  }

}