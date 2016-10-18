package geostat

import scala.collection.mutable.TreeSet
import scala.math._

/**
 * MeshSet
 */
@SerialVersionUID(123L)
class MeshSet extends TreeSet[Face] {

  def this(faces: Set[Face]) {

    this()
    this ++= faces

  }

  /**
   * Return a string representing the GeoJSON map point set representation
   *
   * @return the GeoJSON string
   */
  override def toString(): String = {

    val builder = StringBuilder.newBuilder

    var it = this.iterator

    builder.append("{\"type\":\"FeatureCollection\",\"features\":[\n")

    while (it.hasNext) {
      builder.append(it.next())
      if (it.hasNext)
        builder.append(",")
      builder.append("\n")
    }
    builder.append("]}")

    builder.toString

  }

}