package geostat.kriging.model

/**
 * Exponential SemiVariogram Model
 * 
 * @param c sill
 * @param a range
 * */
class ExponentialModel (c : Double, a : Double) extends SemiVariogramModel {
  
  import scala.math._
  
  require(c > 0.0)
  require(a > 1e-8)
  
  def variogram(h: Double): Double = c * (1.0 - exp(- h / a))

}
