package geostat.kriging.model

/**
 * Sine Hole Effect SemiVariogram Model
 *
 * @param c sill
 * @param a range
 */
class SineHoleEffectModel(val c: Double, a: Double) extends SemiVariogramModel {

  import scala.math._

  require(c > 0.0)
  require(a > 1e-8)

  def variogram(h: Double): Double = {
    require(h>=0.0)
    val r = Pi * h / a
    c * (1.0 - sin(r) / r)
  }

}
