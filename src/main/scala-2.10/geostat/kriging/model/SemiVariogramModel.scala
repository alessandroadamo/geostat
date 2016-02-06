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
  def variogram(h: Float): Float
  def covariogram(h: Float): Float
  

}