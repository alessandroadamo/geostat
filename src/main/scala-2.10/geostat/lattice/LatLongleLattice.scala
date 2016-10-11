package geostat.lattice

import scala.math._
import geostat.MapPoint
import geostat.MapPointSet

/**
 * LatLong Lattice
 *
 * @param angLat angle between points on latitude axes
 * @param angtLon angle between points on longitude axes
 */
class LatLongLattice(angLat: Double = 1.0, angLon: Double = 1.0) extends Lattice {

  require(angLat > 0.0)
  require(angLon > 0.0)

  val vertex = generateVertexSet()

  private def generateVertexSet() = {

    val set = new MapPointSet
    val nlat = floor((MapPoint.MAX_LATITUDE - MapPoint.MIN_LATITUDE).toDouble / angLat).toInt
    val nlon = floor((MapPoint.MAX_LONGITUDE - MapPoint.MIN_LONGITUDE).toDouble / angLon).toInt
    
    
    for (i <- 0 to nlat - 1) {

      var lat = MapPoint.MIN_LATITUDE + i * angLat
      
      for (j <- 0 to nlon - 1) {

        var lon = MapPoint.MIN_LONGITUDE + j * angLon

        set.add(new MapPoint(lat, lon))

      }
    
    }

    set

  }

}