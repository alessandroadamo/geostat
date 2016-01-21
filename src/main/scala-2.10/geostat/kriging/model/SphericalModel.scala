package geostat.kriging.model

import scala.math._

/**
 * Spherical model class
 * 
 * @param r range
 * @param s sill
 * @param a nugget
 * 
 * */
class SphericalModel(val r: Double = 1.0,  val s : Double = 1.0, val a : Double = 0.0) extends Model {

  require(r > 0.0, "range must be greater than 0")
  require(s > 0.0, "sill must be greater than 0")
  require(a >= 0.0, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Double) = {
    var ret = 0.0
    if (h > r)
      ret = s
    else if (h > 0 && h <= r)
      ret = a + (s - a) * 0.5 * ((3.0 * h) / r - (h * h * h) / (r * r * r))
    ret
  }
  def covariogram(h: Double) = {
    var ret = s
    if (h > r)
      ret = 0.0
    else if (h > 0 && h <= r)
      ret = (s - a) * 0.5 * (1.0 - (3.0 * h) / r - (h * h * h) / (r * r * r))
    ret
  }

} 