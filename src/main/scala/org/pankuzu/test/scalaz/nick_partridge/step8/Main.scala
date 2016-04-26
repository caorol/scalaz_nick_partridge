package org.pankuzu.test.scalaz.nick_partridge.step8

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 8
  * -> List を foldLeft する object 作成
  *    def sum[T](x: List[T])(implicit m: Monoid[T]): T = x.foldLeft(m.mzero)(m.mappend) は
  *    引数 x のメソッドに引数 m の m.mzero, m.mappend を渡す形式になっているが、それらを渡して
  *    処理するメソッドを持つオブジェクトを定義し、そのメソッドを使用するように変更する。
  */
//
object FoldLeftList {
  def foldLeft[A, B](x: List[A], b: B, f: (B, A) => B) = x.foldLeft(b)(f)
}

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
  // FoldLeftList オブジェクトの foldLeft メソッドを使用するように変更
//  def sum[T](x: List[T])(implicit m: Monoid[T]): T = x.foldLeft(m.mzero){m.mappend}
  def sum[T](x: List[T])(implicit m: Monoid[T]): T = FoldLeftList.foldLeft(x, m.mzero, m.mappend)

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    p(sum(List("aaa","bbb","ccc")))
    p(sum(List(1,2,3,4))(multMonoid))
    println
  }

}

