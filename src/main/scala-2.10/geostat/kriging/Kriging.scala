package geostat.kriging

//import geostat.MapPointSet
import geostat.kriging.model.Model
import geostat.kriging.model.GaussianModel
import scala.tools.nsc.util.TreeSet
import geostat.MapPoint


//class Kriging (val points : MapPointSet, val model : Model = new GaussianModel()) {
class Kriging (val points : TreeSet[MapPoint], val model : Model = new GaussianModel()) {

  //private val K = dense(points.size, points.size)
    
  
}
