package geostat

import scala.math._

/**
 * Link abstract class
 *
 * @param nodeA first node
 * @param nodeB second node
 * @param nodeB second node
 */
@SerialVersionUID(123L)
class Face(val nodeA: MapPoint, val nodeB: MapPoint, val nodeC: MapPoint) extends Serializable with Ordered[Face] {

  require(nodeA != null)
  require(nodeB != null)
  require(nodeC != null)

  override def hashCode: Int = {

    val prime = 31L;
    var result = 1L;
    val arr: Array[MapPoint] = Array(nodeA, nodeB, nodeC).sorted

    arr.foreach { x => result = prime * result + (x.key ^ (x.key >>> 32L)) }

    result.toInt

  }

  override def equals(that: Any): Boolean = {

    val tt: Face = that.asInstanceOf[Face]

    val arrA: Array[MapPoint] = Array(nodeA, nodeB, nodeC).sorted
    val arrB: Array[MapPoint] = Array(tt.nodeA, tt.nodeB, tt.nodeC).sorted

    (arrA(0).equals(arrB(0)) && (arrA(1).equals(arrB(1))) && arrA(2).equals(arrB(2)))

  }

  def compare(that: Face) = {

    val arrA: Array[MapPoint] = Array(nodeA, nodeB, nodeC).sorted
    val arrB: Array[MapPoint] = Array(that.nodeA, that.nodeB, that.nodeC).sorted

    var res = arrA(0).key.compare(arrB(0).key)
    if (res == 0) {
      res = arrA(1).key.compare(arrB(1).key)
      if (res == 0) {
        res = arrA(2).key.compare(arrB(2).key)
        res
      } else res
    }

    res

  }

  override def toString() = {

    val builder = StringBuilder.newBuilder

    builder.append("{\"type\":\"Feature\"")
    builder.append(",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[")
    builder.append(nodeA.longitude)
    builder.append(",")
    builder.append(nodeA.latitude)
    builder.append("],[")
    builder.append(nodeB.longitude)
    builder.append(",")
    builder.append(nodeB.latitude)
    builder.append("],[")
    builder.append(nodeC.longitude)
    builder.append(",")
    builder.append(nodeC.latitude)
    builder.append("],[")
    builder.append(nodeA.longitude)
    builder.append(",")
    builder.append(nodeA.latitude)
    builder.append("]]]},\"properties\":")
    builder.append("null")

    builder.append("}\n")

    builder.toString()

  }

}
