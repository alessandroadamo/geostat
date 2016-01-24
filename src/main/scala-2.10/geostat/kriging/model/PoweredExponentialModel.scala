package geostat.kriging.model

import scala.math._

/**
 * Powered Exponential model class
 *
 * @param r range
 * @param s sill
 * @param a nugget
 * @param p power
 *
 */
class PoweredExponentialModel(val r: Float = 1.0f, val s : Float = 1.0f, val a : Float = 0.0f, val p : Float = 1.0f) extends Model {

  require(r > 0.0f, "range must be greater than 0")
  require(s > 0.0f, "sill must be greater than 0")
  require(a >= 0.0f, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Float) = {
    var ret = 0.0f
    if (h > 0.0f)
      ret = a + (s - a) * (1.0f - exp(-3.0f * pow(h, p) / r).toFloat)
    ret
  }
  
  def covariogram(h: Float) = {
    var ret = s
    if (h > 0.0f)
      ret = (s - a) * exp(-3.0f * pow(h, p) / r).toFloat
    ret
  }

}