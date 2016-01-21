package geostat.kriging.model

/**
 * Model trait
 * */
trait Model {

  /**
   * Compute variogram 
   * 
   * @param distance
   * @return the computed variogram 
   * 
   * */
  def variogram(h: Double): Double
  def covariogram(h: Double): Double
  

}