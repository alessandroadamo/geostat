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

    while (it.hasNext)
      builder.append(it.next())

    builder.toString

  }

}