package day02

object Day02 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")

  def main(args: Array[String]): Unit = {
    val input = fileInput

    val x = input.map(line => {
      val first = line.split("-").head.toInt
      val second = line.split(" ").head.split("-").last.toInt
      val c = line.split(":").head.last
      val pw = line.split(" ").last

      val count = pw.count(_.equals(c))
      val valid1 = count >= first && count <= second
      val valid2 = pw(first - 1).equals(c) ^ pw(second - 1).equals(c)
      (valid1, valid2)
    })

    println("Part 1: " + x.count(_._1))
    println("Part 2: " + x.count(_._2))

  }

}
