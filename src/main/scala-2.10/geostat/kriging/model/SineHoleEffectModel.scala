package geostat.kriging.model

/**
 * Sine Hole Effect SemiVariogram Model
 *
 * @param c sill
 * @param a range
 */
class SineHoleEffectModel(c: Double, a: Double) extends SemiVariogramModel {

  import scala.math._

  require(c > 0.0)
  require(a > 1e-8)

  def variogram(h: Double): Double = {
    val r = Pi * h / a
    c * (1.0 - sin(r) / r)
  }

}
