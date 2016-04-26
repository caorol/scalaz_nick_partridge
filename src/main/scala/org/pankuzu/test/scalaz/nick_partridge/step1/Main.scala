package org.pankuzu.test.scalaz.nick_partridge.step1

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 1
  */
object Main {
  def sum(x: List[Int]): Int = x.foldLeft(0){(a, b) => a + b}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    println
  }

}
