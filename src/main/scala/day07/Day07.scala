package day07

object Day07 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")
  lazy val testInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n")


  def main(args: Array[String]): Unit = {
    val input = fileInput

    val myBag: String = "shiny gold"

    val bags = input.map(l => {
      val bag = l.split(" bags").head
      val content = l.split("contain ").last
      if (content.equals("no other bags.")) {
        (bag, List.empty[(String, Int)])
      } else {
        val b = content.split(",").map(_.trim.split(" bag").head).toList.map(x => {
          (x.drop(x.split(" ").head.length + 1), Integer.valueOf(x.split(" ").head).toInt)
        })
        (bag, b)
      }
    }).toMap

    println("Part 1: " + bags.keySet.count(b => contains(b, myBag, bags)))
    println("Part 2: " + requires(myBag, bags))
  }

  def contains(current: String, key: String, m: Map[String, List[(String, Int)]]): Boolean = {
    if (m(current).map(_._1).contains(key)) {
      true
    } else {
      if (m(current).isEmpty) {
        false
      } else {
        m(current).map(_._1).map(x => contains(x, key, m)).reduce(_ || _)
      }
    }
  }

  def requires(current: String, m: Map[String, List[(String, Int)]]): Int = {
    m(current).map(x => x._2 + x._2 * requires(x._1, m)).sum
  }

}
