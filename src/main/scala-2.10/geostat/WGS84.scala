package geostat

/**
 * World Geodetic System 1984 (WGS84)
 */
trait WGS84 {

  val A = 6378137.0f                 // Semi-major axis a
  val B = 6356752.314245f            // Semi-minor axis b
  val IF = 298.257223563f            // Inverse flattening (1/f)
  val R = (2.0f * A + B) / 3.0f      // Radius for spherical Earth 

}
