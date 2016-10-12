package geostat

/**
 * DirectedLink class
 *
 * @param nodeA first node
 * @param nodeB second node
 */
@SerialVersionUID(123L)
class DirectedLink(nodeA: MapPoint, nodeB: MapPoint) extends Link(nodeA, nodeB) {

  override def hashCode: Int = {

    val prime = 31L;
    var result = 1L;
    val a = nodeA.key;
    val b = nodeA.key;

    result = prime * result + (a ^ (a >>> 32L))
    result = prime * result + (b ^ (b >>> 32L))
    result.toInt

  }

}
