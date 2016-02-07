package geostat.kriging.model

/**
 * SemiVariogram Model trait
 * */
trait SemiVariogramModel  {
  
  val c : Double // sill
  
  /**
   * Compute the Variogram 
   * 
   * @param distance
   * @return the computed variogram 
   * */
  def variogram(h: Double): Double
  
  /**
   * Compute the Co-Variogram 
   * 
   * @param distance
   * @return the computed co-variogram 
   * */
  def covariogram(h: Double): Double = c - variogram(h)
  
}
