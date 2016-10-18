package geostat.kriging.model

/**
 * Nested SemiVariogram Model
 * 
 * The resulting semivariogram is the linear combination of the two 
 * semivariograms passed as arguments
 * 
 * 
 * @param gamma1 first semivariogram model
 * @param gamma2 second semivariogram model
 * */
class NestedModel(gamma1: SemiVariogramModel, gamma2: SemiVariogramModel) extends SemiVariogramModel {

  val c : Double = gamma1.c + gamma2.c
  
  def variogram(h: Double): Double = gamma1.variogram(h) + gamma2.variogram(h) 
  
}