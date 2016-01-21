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
class WaveModel(val w: Double = 1.0, val s : Double = 1.0, val a : Double = 0.0) extends Model {

  require(w > 0.0, "wave intensity must be greater than 0")
  require(s > 0.0, "sill must be greater than 0")
  require(a >= 0.0, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Double) = {
    var ret = 0.0
    if (h > 0)
      ret = a + (s - a) * (1.0 - w * sin(h / w) / h)
    ret
  }
  def covariogram(h: Double) = {
    var ret = s
    if (h > 0)
      ret = (s - a) * (w * sin(h / w) / h)
    ret
  }
}