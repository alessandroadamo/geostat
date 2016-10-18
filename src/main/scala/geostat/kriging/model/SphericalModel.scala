package geostat.kriging.model

/**
 * Spherical SemiVariogram Model
 *
 * @param c sill
 * @param a range
 */
class SphericalModel(val c: Double, a: Double) extends SemiVariogramModel {

  import scala.math._

  require(c > 0.0)
  require(a > 1e-8)

  def variogram(h: Double): Double = {
    require(h>=0.0)
    val r = h / a
    if (h <= a) c * ((3.0 / 2.0) * r - 0.5 * pow(r, 3.0))
    else c
  }
  
}
