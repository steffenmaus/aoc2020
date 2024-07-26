package day04

object Day04 {
  lazy val fileInput: Array[Array[String]] = io.Source.fromInputStream(getClass.getResourceAsStream("input.txt")).mkString.trim.filter(_ != '\r').split("\n\n").map(_.split("\n"))
  lazy val testInput: Array[Array[String]] = io.Source.fromInputStream(getClass.getResourceAsStream("testInput.txt")).mkString.trim.filter(_ != '\r').split("\n\n").map(_.split("\n"))


  def main(args: Array[String]): Unit = {
    val input = fileInput

    val fields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").map(_.appended(':'))
    val colors = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val hex = "abcdef0123456789".toCharArray.toSet

    val c = input.map(block => {
      val a = block.flatMap(_.split(' ')).toSet
      val r = fields.map(x => a.exists(_.startsWith(x))).reduce(_ && _)
      if (r) {
        val byr = a.filter(_.startsWith("byr")).head.split(':').last.toInt
        val a1 = byr >= 1920 && byr <= 2002

        val iyr = a.filter(_.startsWith("iyr")).head.split(':').last.toInt
        val a2 = iyr >= 2010 && iyr <= 2020

        val eyr = a.filter(_.startsWith("eyr")).head.split(':').last.toInt
        val a3 = eyr >= 2020 && eyr <= 2030

        val hgt = a.filter(_.startsWith("hgt")).head.split(':').last
        val a4 = (hgt.endsWith("cm") && hgt.dropRight(2).toInt >= 150 && hgt.dropRight(2).toInt <= 193) || (hgt.endsWith("in") && hgt.dropRight(2).toInt >= 59 && hgt.dropRight(2).toInt <= 76)

        val hcl = a.filter(_.startsWith("hcl")).head.split(':').last
        val a5 = hcl.startsWith("#") && hcl.drop(1).forall(hex.contains)

        val ecl = a.filter(_.startsWith("ecl")).head.split(':').last
        val a6 = colors.contains(ecl)

        val pid = a.filter(_.startsWith("pid")).head.split(':').last
        val a7 = pid.length == 9 && pid.forall(_.isDigit)

        (r, a1 && a2 && a3 && a4 && a5 && a6 && a7)
      } else {
        (false, false)
      }
    })

    println("Part 1: " + c.count(_._1.equals(true)))
    println("Part 2: " + c.count(_._2.equals(true)))
  }

}
