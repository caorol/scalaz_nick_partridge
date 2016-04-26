package org.pankuzu.test.scalaz.nick_partridge.step5

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 5
  * -> sum メソッドのパラメタが増えている（IntMonoidの分）
  *    sum(List(1,2,3,4), IntMonoid)
  *    IntMonoid はいつも同じなので implicit parameter を使用してパラメタ指定がない場合暗黙的に IntMonoid が渡されるようにする
  */
object Main {
  // implicit 定義
  implicit val intMonoid = IntMonoid
  // implicit parameter 化
  def sum[T](x: List[T])(implicit m: Monoid[T]): T = x.foldLeft(m.mzero){m.mappend}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    // IntMonoid が省略可能になった
    p(sum(List(1,2,3,4)))
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
