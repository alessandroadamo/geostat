package geostat.kriging.model

/**
 * Power SemiVariogram Model
 *
 * @param c sill
 * @param w power
 */
class PowerModel(val c: Double, a: Double) extends SemiVariogramModel {

  import scala.math._

  require(c > 0.0)
  require(a >= 0.0 && a < 2.0)

  def variogram(h: Double): Double = { 
    require(h>=0.0)
    c * pow(h, a)
  }

}
