package geostat.kriging.model

/**
 * PentaSpherical SemiVariogram Model
 * 
 * @param c sill
 * @param a range
 * */
class PentaSphericalModel (c : Double, a : Double) {
  
  import scala.math._

  require(c > 0.0)
  require(a > 1e-8)

  def variogram(h: Double): Double = {
    val r = h / a
    if (h <= a) (15.0/8.0 * pow(r, 2.0) - 5.0/4.0 * pow(r, 3.0) + 3.0/8.0 * pow(r, 5.0))
    else c
  }
  
}
