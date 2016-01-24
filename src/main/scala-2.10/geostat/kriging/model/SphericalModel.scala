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
class SphericalModel(val r: Float = 1.0f,  val s : Float = 1.0f, val a : Float = 0.0f) extends Model {

  require(r > 0.0f, "range must be greater than 0")
  require(s > 0.0f, "sill must be greater than 0")
  require(a >= 0.0f, "nugget must be greater than or equal 0")
  require(s >= a, "sill must be greater than nugget")

  def variogram(h: Float) = {
    var ret = 0.0f
    if (h > r)
      ret = s
    else if (h > 0.0f && h <= r)
      ret = a + (s - a) * 0.5f * ((3.0f * h) / r - (h * h * h) / (r * r * r))
    ret
  }
  
  def covariogram(h: Float) = {
    var ret = s
    if (h > r)
      ret = 0.0f
    else if (h > 0.0f && h <= r)
      ret = (s - a) * 0.5f * (1.0f - (3.0f * h) / r - (h * h * h) / (r * r * r))
    ret
  }

} 