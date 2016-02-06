package geostat.kriging.model

/**
 * Power SemiVariogram Model
 * 
 * @param c sill
 * @param w power
 * */
class PowerModel (c : Double, a : Double) extends SemiVariogramModel {
  
  import scala.math._
  
  require(c > 0.0)
  require(a > 0.0 && a < 2.0)
  
  def variogram(h: Double): Double = c * pow(h, a)
  
}
