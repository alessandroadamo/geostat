package geostat

/**
 * World Geodetic System 1984 (WGS84)
 */
trait WGS84 {

  val A = 6378137.0                 // Semi-major axis a
  val B = 6356752.314245            // Semi-minor axis b
  val IF = 298.257223563            // Inverse flattening (1/f)
  val R = (2.0 * A + B) / 3.0       // Radius for spherical Earth 

}
