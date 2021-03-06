package geostat

/**
 * UndirectedLink class
 *
 * @param nodeA first node
 * @param nodeB second node
 */
@SerialVersionUID(123L)
class UndirectedLink(nodeA: MapPoint, nodeB: MapPoint) extends Link(nodeA, nodeB) {

  override def hashCode: Int = {

    val prime = 31L;
    var result = 1L;
    var a = 0L;
    var b = 0L;

    if (nodeA.key < nodeB.key) {
      a = nodeA.key
      b = nodeB.key
    } else {
      a = nodeB.key
      b = nodeA.key
    }

    result = prime * result + (a ^ (a >>> 32L))
    result = prime * result + (b ^ (b >>> 32L))
    result.toInt

  }

  override def equals(that: Any): Boolean = {

    val tt: UndirectedLink = that.asInstanceOf[UndirectedLink]
    ((nodeA.equals(tt.nodeA) && (nodeB.equals(tt.nodeB))) ||
      (nodeA.equals(tt.nodeB) && (nodeB.equals(tt.nodeA))))
  }

}
