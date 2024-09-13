package day10

import scala.collection.mutable

object Day10 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")
  lazy val testInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n")

  private val cache: mutable.Map[Int, Long] = mutable.Map.empty

  def main(args: Array[String]): Unit = {
    val input = fileInput.map(_.toInt)

    val sorted = input.appendedAll(List(0, input.max + 3)).sorted

    val deltas = sorted.drop(1).zip(sorted.dropRight(1)).map(x => x._1 - x._2)

    val p1 = deltas.count(_.equals(1)) * deltas.count(_.equals(3))
    println("Part 1: " + p1)

    println("Part 2: " + f(0, sorted.toList))
  }

  def f(current: Int, list: List[Int]): Long = {
    if (cache.contains(current)) {
      return cache(current)
    }
    if (current == list.max) {
      cache(current) = 1L
      return 1L
    }
    val r = list
      .filter(x => x > current && x <= current + 3)
      .map(candidate => f(candidate, list))
      .sum
    cache(current) = r
    return r
  }

}
