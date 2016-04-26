package org.pankuzu.test.scalaz.nick_partridge.step6

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 6
  * -> List[Int] だけではなく List[String] も扱えるようにする
  *    object Monoid 内に implicit object として IntMonoid, StringMonoid を定義する
  *    p(sum(List(... よりも前に記載しておかないといけない
  */
// object Monoid を定義
object Monoid {
  // Int をとる Monoid
  implicit object IntMonoid extends Monoid[Int]{
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  // String をとる Monoid
  implicit object StringMonoid extends Monoid[String]{
    def mappend(a: String, b: String): String = a + b
    def mzero: String = ""
  }
}

// Monoid 型を定義
trait Monoid[A] {
  def mappend(a: A, b: A): A
  def mzero: A
}

object Main {
  // implicit 定義が不要になる
//  implicit val intMonoid = IntMonoid
  // implicit parameter 化
  def sum[T](x: List[T])(implicit m: Monoid[T]): T = x.foldLeft(m.mzero){m.mappend}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    // List[Int], List[String] どちらも渡せる
    p(sum(List(1,2,3,4)))             // List が Int なので IntMonoid が呼ばれる
    p(sum(List("aaa","bbb","ccc")))   // List が String なので StringMonoid が呼ばれる
    println
  }

}

