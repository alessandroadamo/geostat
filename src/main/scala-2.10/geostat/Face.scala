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
abstract class Face(val nodeA: MapPoint, val nodeB: MapPoint, val nodeC: MapPoint) extends Serializable {

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

  override def toString() = {

    val builder = StringBuilder.newBuilder

    builder.append("{\"nodes\":[")
    builder.append(nodeA.key)
    builder.append(",")
    builder.append(nodeB.key)
    builder.append(",")
    builder.append(nodeC.key)
    builder.append("]}\n")

    builder.toString()

  }

}
