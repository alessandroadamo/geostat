package geostat.kriging.model

/**
 * Nugget Model
 * 
 * @param c sill
 * */
class NuggetModel (c: Double) extends SemiVariogramModel {

  require(c > 0.0)
  
  def variogram(h: Double): Double = if (h < 1e-8) 0.0 else c

}

