package geostat.kriging.model

import scala.math._

/**
 * Gaussian model class
 *
 * @param r range
 * @param s sill
 * @param a nugget
 *
 */
class GaussianModel(val r: Float = 1.0f, val s: Float = 1.0f, val a: Float = 0.0f) extends Model {

  require(r > 0.0f, "range must be greater than 0")
  require(s > 0.0f, "sill must be greater than 0")
  require(a >= 0.0f, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Float) = {
    var ret = 0.0f
    if (h > r)
      ret = s
    else if (h > 0.0f && h <= r)
      ret = a + (s - a) * (1.0f - exp(-3.0f * (h * h) / (r * r)).toFloat)
    ret
  }
  def covariogram(h: Float) = {
    var ret = s
    if (h > r)
      ret = 0.0f
    else if (h > 0.0f && h <= r)
      ret = (s - a) * exp(-3.0f * (h * h) / (r * r)).toFloat
    ret
  }

} 