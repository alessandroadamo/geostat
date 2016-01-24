package geostat.kriging.model

import scala.math._

/**
 * Wave model class
 *
 * @param w wave intensity
 * @param s sill
 * @param a nugget
 *
 */
class WaveModel(val w: Float = 1.0f, val s : Float = 1.0f, val a : Float = 0.0f) extends Model {

  require(w > 0.0f, "wave intensity must be greater than 0")
  require(s > 0.0f, "sill must be greater than 0")
  require(a >= 0.0f, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Float) = {
    var ret = 0.0f
    if (h > 0.0f)
      ret = a + (s - a) * (1.0f - w * sin(h / w).toFloat / h)
    ret
  }
  
  def covariogram(h: Float) = {
    var ret = s
    if (h > 0.0f)
      ret = (s - a) * (w * sin(h / w).toFloat / h)
    ret
  }
}