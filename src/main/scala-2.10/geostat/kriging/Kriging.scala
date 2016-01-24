package geostat.kriging

import geostat.MapPointSet
import org.apache.mahout.math._
import scalabindings._
import geostat.kriging.model.Model
import geostat.kriging.model.GaussianModel


class Kriging (val points : MapPointSet, val model : Model = new GaussianModel()) {

  private val K = dense(points.size, points.size)
    
  
}
