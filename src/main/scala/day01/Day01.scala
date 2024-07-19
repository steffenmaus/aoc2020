package day01

object Day01 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")


  def main(args: Array[String]): Unit = {
    val input = fileInput.map(_.toInt)

    var p1 = 0
    var p2 = 0

    for (a <- input) {
      for (b <- input) {
        if (a + b == 2020) {
          p1 = a * b
        }
        for (c <- input) {
          if (a + c + b == 2020) {
            p2 = a * b * c
          }
        }
      }
    }

    println("Part 1: " + p1)
    println("Part 2: " + p2)

  }

}
