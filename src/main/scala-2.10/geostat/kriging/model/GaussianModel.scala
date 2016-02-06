package geostat.kriging.model

/**
 * Gaussian Model
 * 
 * @param c sill
 * @param a range
 * */
class GaussianModel (c : Double, a : Double) extends SemiVariogramModel {
  
  import scala.math._
  
  require(c > 0.0)
  require(a > 1e-8)
  
  def variogram(h: Double): Double = c * (1.0 - exp(- h*h / (a*a)))

}
