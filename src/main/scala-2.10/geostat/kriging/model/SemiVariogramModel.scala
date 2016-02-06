package geostat.kriging.model

/**
 * SemiVariogram Model trait
 * */
trait SemiVariogramModel {

  /**
   * Compute the Variogram 
   * 
   * @param distance
   * @return the computed variogram 
   * */
  def variogram(h: Double): Double

}
