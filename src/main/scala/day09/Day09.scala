package day09

import scala.collection.mutable

object Day09 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")
  lazy val testInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n")

  def main(args: Array[String]): Unit = {
    val input = fileInput
    val batch = 25

    val p1 = f(input, batch)

    println("Part 1: " + p1)

    var left = 0
    var right = 1
    var s = input(left).toLong + input(right).toLong
    while (s != p1) {
      if (s < p1) {
        right = right + 1
        s = s + input(right).toLong
      } else if (s > p1) {
        s = s - input(left).toLong
        left = left + 1
      }
    }

    val range = input.zipWithIndex.filter(_._2 >= left).filter(_._2 <= right).map(_._1.toInt)
    val p2 = range.min + range.max
    println("Part 2: " + p2)

  }

  def f(a: Array[String], batch: Int): Int = {
    a.indices.foreach(l => {
      if (l >= batch) {
        val n = a(l).toInt
        val mySet: mutable.Set[Int] = mutable.HashSet.empty
        val prev = a.toList.slice(l - batch, l).map(_.toInt)
        for (p1 <- prev) {
          for (p2 <- prev) {
            if (p1 != p2) {
              mySet.addOne(p1 + p2)
            }
          }
        }
        if (!mySet.contains(n)) {
          return n
        }
      }
    })
    0
  }


}
