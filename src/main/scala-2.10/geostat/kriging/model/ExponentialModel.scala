package geostat.kriging.model

import scala.math._
import geostat.kriging.model.Model

/**
 * Exponential model class
 *
 * @param r range
 * @param s sill
 * @param a nugget
 *
 */
class ExponentialModel(val r: Float = 1.0f, val s : Float = 1.0f, val a : Float = 0.0f) extends Model {

  require(r > 0.0, "range must be greater than 0")
  require(s > 0.0, "sill must be greater than 0")
  require(a >= 0.0, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Float) = {
    var ret = 0.0f
    if (h > 0.0f)
      ret = a + (s - a) * (1.0f - exp(-(3.0f * h) / r).toFloat)
    ret
  }
  
  def covariogram(h: Float) = {
    var ret = s
    if (h > 0.0f)
      ret = (s - a) * exp(-(3.0f * h) / r).toFloat
    ret
  }

}