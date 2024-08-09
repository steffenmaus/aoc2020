package day06

object Day06 {
  lazy val fileInput: Array[Array[String]] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n\n").map(_.split("\n"))
  lazy val testInput: Array[Array[String]] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n\n").map(_.split("\n"))

  def main(args: Array[String]): Unit = {
    val input = fileInput

    val p1 = input.map(group => {
      group.reduce((a, b) => a.appendedAll(b)).toCharArray.toSet.size
    }).sum

    val p2 = input.map(group => {
      val cs = group.map(_.toCharArray.toSet)
      cs.head.count(c => cs.forall(_.contains(c)))
    }).sum

    println("Part 1: " + p1)
    println("Part 2: " + p2)
  }

}
