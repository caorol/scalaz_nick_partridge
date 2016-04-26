package org.pankuzu.test.scalaz.nick_partridge.step7

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 7
  * -> 暗黙的パラメタ（implicit parameter）にパラメタを渡す
  */
object Monoid {
  implicit object IntMonoid extends Monoid[Int]{
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  implicit object StringMonoid extends Monoid[String]{
    def mappend(a: String, b: String): String = a + b
    def mzero: String = ""
  }
}

trait Monoid[A] {
  def mappend(a: A, b: A): A
  def mzero: A
}

object Main {
  val multMonoid = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }
  def sum[T](x: List[T])(implicit m: Monoid[T]): T = x.foldLeft(m.mzero){m.mappend}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    p(sum(List("aaa","bbb","ccc")))
    // implicit parameter に明示的に multMonoid を渡す
    p(sum(List(1,2,3,4))(multMonoid))
    println
  }

}

