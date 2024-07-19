package day03

object Day03 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")
  lazy val testInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n")

  def main(args: Array[String]): Unit = {
    val input = getInputAs2DArray(fileInput)

    val a = f(input, 1, 1)
    val b = f(input, 3, 1)
    val c = f(input, 5, 1)
    val d = f(input, 7, 1)
    val e = f(input, 1, 2)

    println("Part 1: " + b)
    println("Part 2: " + a.toLong * b * c * d * e)

  }


  def f(grid: Array[Array[Char]], right: Int, down: Int): Int = {
    var x = 0
    var y = 0
    var count = 0

    while (y < grid.length - 1) {
      x = (x + right) % grid.head.length
      y = y + down
      if (grid(y)(x).equals('#')) {
        count = count + 1
      }
    }
    count
  }

  def getInputAs2DArray(input: Array[String]): Array[Array[Char]] = {
    val grid: Array[Array[Char]] = new Array(input.length)
    for (y <- grid.indices) {
      grid(y) = input(y).toCharArray
    }
    grid
  }

}
