package day05

object Day05 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")

  def main(args: Array[String]): Unit = {
    val input = fileInput

    val seats = input.map(line => {
      val row = Integer.parseInt(line.take(7).replace('F', '0').replace('B', '1'), 2)
      val col = Integer.parseInt(line.takeRight(3).replace('L', '0').replace('R', '1'), 2)

      (row, col)
    })

    println("Part 1: " + seats.map(t => t._1 * 8 + t._2).max)

    val firstRow = seats.map(_._1).min
    val lastRow = seats.map(_._1).max

    for (r <- firstRow + 1 until lastRow) {
      for (c <- 0 to 7) {
        if (!seats.contains(r, c)) {
          val id = r * 8 + c
          println("Part 2: " + id)
        }
      }
    }

  }

}
