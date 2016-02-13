package geostat.kde

import geostat.MapPointSet
import geostat.MapPoint
import breeze.linalg.DenseMatrix
import breeze.linalg._

/**
 * Kernel Density
 *
 * @param h1 latitude anisotropic parameter
 * @param h2 longitude anisotropic parameter
 * @param h12 diagonal anisotropic parameter
 */
class KernelDensity(h1: Double, h2: Double, h12: Double, dataset: MapPointSet) {

  import scala.math._

  require(h1 > 0.0)
  require(h2 > 0.0)

  private var H = DenseMatrix((h1 * h1, h12), (h12, h2 * h2))
  private var Hinv = inv(H)
  private var idet = 1.0 / det(H)

  /**
   * Kernel Density Estimation
   *
   * @param h1 latitude anisotropic parameter
   * @param h2 longitude anisotropic parameter
   */
  def this(h1: Double, h2: Double, dataset: MapPointSet) { this(h1, h2, 0.0, dataset) }

  /**
   * Kernel Density Estimation
   *
   * @param h isotropic parameter
   * @param dataset dataset
   */
  def this(h: Double, dataset: MapPointSet) { this(h, h, 0.0, dataset) }

  /**
   * Kernel Density Estimation
   *
   * @param dataset dataset
   */
  def this(dataset: MapPointSet) {

    this(1.0, 1.0, 0.0, dataset)

  }

  // Gaussian kernel 
  private def K(x: DenseVector[Double]): Double = exp(-0.5 * (Transpose(x) * x)) / (2.0 * Pi)

  /**
   * Compute 2D Kernel Density
   *
   * @param grid compute the density in points passed as argument
   */
  def kde(grid: MapPointSet): MapPointSet = {

    val radius = sqrt(max(H(0, 0), H(1, 1)))

    grid.foreach { pt =>

      val neighbor = dataset.radiusQuery(pt, radius);
      val n = neighbor.size

      val x = DenseVector(pt.latitude, pt.longitude)

      pt.value = if (n > 0) neighbor.map(pn => idet * K(Hinv * (x - DenseVector(pn.latitude, pn.longitude)))).sum / n else 0.0

    }

    grid

  }

}

object KernelDensity {

  import scala.math._

  /**
   * Silverman's rule of thumb.
   *
   * @param dataset dataset point
   * @return suggested h1 and h2 in a tuple
   */
  def silvermanRule(dataset: MapPointSet): (Double, Double) = {

    val stddev = dataset.stddev()
    val n = dataset.size

    (pow(n, -1.0 / 6.0) * stddev._1, pow(n, -1.0 / 6.0) * stddev._2)
  }

}
