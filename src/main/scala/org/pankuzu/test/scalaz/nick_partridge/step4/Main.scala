package org.pankuzu.test.scalaz.nick_partridge.step4

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 4
  * -> sum メソッド定義で型指定されている List[Int] を型パラメタの使用に変更
  */
object Main {
  // 型パラメタの使用に変更
  def sum[T](x: List[T], m: Monoid[T]): T = x.foldLeft(m.mzero){m.mappend}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4), IntMonoid))
    println
  }

}

// Monoid 型を継承
object IntMonoid extends Monoid[Int]{
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0
}

// Monoid 型を定義
trait Monoid[A] {
  def mappend(a: A, b: A): A
  def mzero: A
}
