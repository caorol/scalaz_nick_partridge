package org.pankuzu.test.scalaz.nick_partridge.step9

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 9
  * -> sum をもっと一般化
  *    def sum[T](x: List[T])(implicit m: Monoid[T]): T = FoldLeftList.foldLeft(x, m.mzero, m.mappend)
  *    をもっと一般化する
  *    -> List への型パラメタ適用
  */
// List に型パラメタを適用するための一般化されたインタフェース
trait FoldLeft[F[_]] {
  def foldLeft[A, B](x: F[A], b: B, f: (B, A) => B): B
}

// FoldLeft インタフェースを継承して foldLeft をオーバライド
object FoldLeft {
  implicit object FoldLeftList extends FoldLeft[List]{
    def foldLeft[A, B](x: List[A], b: B, f: (B, A) => B) = x.foldLeft(b)(f)
  }
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
  // List 部を型パラメタ化
//  def sum[T](x: List[T])(implicit m: Monoid[T]): T = FoldLeftList.foldLeft(x, m.mzero, m.mappend)
  def sum[M[_], T](x: M[T])(implicit m: Monoid[T], fl: FoldLeft[M]): T = fl.foldLeft(x, m.mzero, m.mappend)

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    p(sum(List("aaa","bbb","ccc")))
//    p(sum(List(1,2,3,4))(multMonoid))   // 今はエラーになるのでコメント
    println
  }

}

