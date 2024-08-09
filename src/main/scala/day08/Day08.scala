package day08

import scala.collection.mutable

object Day08 {
  lazy val fileInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n")
  lazy val testInput: Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n")

  def main(args: Array[String]): Unit = {
    val input = fileInput

    val p1 = f(input, true)

    val p2 = input.indices.map(p => {
      val tempInput = input.clone()
      if (tempInput(p).startsWith("jmp")) {
        tempInput(p) = "nop".appendedAll(tempInput(p).drop(3))
      } else if (tempInput(p).startsWith("nop")) {
        tempInput(p) = "jmp".appendedAll(tempInput(p).drop(3))
      }
      f(tempInput, false)
    }).min

    println("Part 1: " + p1)
    println("Part 2: " + p2)

  }

  def f(a: Array[String], p1: Boolean): Int = {
    var pointer = 0
    var acc = 0
    val mySet: mutable.Set[Int] = mutable.HashSet.empty
    while (!mySet.contains(pointer)) {
      if (pointer == a.length) {
        return acc
      }
      mySet.addOne(pointer)
      val split = a(pointer).split(" ")
      val op = split(0)
      val b = split(1)
      val number = if (b.startsWith("-")) -b.drop(1).toInt else b.drop(1).toInt

      if (op.equals("jmp")) {
        pointer = pointer - 1 + number
      } else if (op.equals("acc")) {
        acc = acc + number
      }
      pointer = pointer + 1
    }
    if (p1) acc else Integer.MAX_VALUE
  }

}
