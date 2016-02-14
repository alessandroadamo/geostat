package geostat.kriging.model

import geostat.MapPointSet

/**
 * Empirical Variogram class
 *
 * @param points the map point set
 */
class EmpiricalVariogram(points: MapPointSet) {

  import scala.math._

  /**
   * Compute the variogram
   *
   * @param lag an appropriate lag increment. The lag increment defines distances
   * at which the variogram is calculated.
   * @param tol tolerance for the lag increment.
   * The tolerance estrablishes distance bins for the lag increment, to accomodate enevently spaced observations.
   * Tolerance must be greater than 0.0 and less or equal thatn 1/2.
   * @param nlags the number of lags which the variogram will be calculated.
   * The number of lags is conjunction with the size of lag increment
   * will define the total distance over which a variogram is calculated.
   * @param cressie if true compute the variogram with Cressie-hawkins robust variogram estimator, if false throw Matheron's
   * classical variogram estimator.
   * @return the array of the computed variogram
   */
  def variogram(lag: Double, tol: Double = 0.5, nlags: Int = 30, cressie: Boolean = true): Array[Double] = {

    require(nlags > 0)
    require(tol > 0.0 && tol <= 0.5)
    require(lag > 0.0)

    val variog = new Array[Double](nlags)

    for (i <- 0 to nlags) {

      val llag = i * lag
      val minh = if ((llag - tol * lag) > 0.0) (llag - tol * lag) else 0.0
      val maxh = llag + tol * lag

      if (cressie == true) { // Cressie-hawkins 

        val dists = points.flatMap(
          pt => points.radiusQuery(pt, minh, maxh)
            .map(pt2 => pow(abs(pt.value - pt2.value), 0.5)))
        val ss = dists.sum
        val Nh = if (dists.size > 0) dists.size else 1
        val Ch = 0.457 + 0.494 / Nh + 0.494 / (Nh * Nh)
        variog(i) = pow(ss / Nh, 4.0) / (2.0 * Ch)

      } else { // Matheron

        val dists = points.flatMap(
          pt => points.radiusQuery(pt, minh, maxh)
            .map(pt2 => pow(abs(pt.value - pt2.value), 2.0)))
        val ss = dists.sum
        val Nh = if (dists.size > 0) dists.size else 1
        variog(i) = ss / (2.0 * Nh)

      }

    }

    variog

  }

}