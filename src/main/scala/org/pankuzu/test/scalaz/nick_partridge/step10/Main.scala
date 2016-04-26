package org.pankuzu.test.scalaz.nick_partridge.step10

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 10
  * -> 再度パラメタを明示的に渡してみる
  *    9 でコメントアウトしていた sum(List(1,2,3,4))(multMonoid) を正しく動作させるために
  *    暗黙的に解決できない部分を明示的に指定する
  *    sum(List(1,2,3,4))(multMonoid, implicitly[FoldLeft[[List]])
  */
trait FoldLeft[F[_]] {
  def foldLeft[A, B](x: F[A], b: B, f: (B, A) => B): B
}

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

  def sum[M[_], T](x: M[T])(implicit m: Monoid[T], fl: FoldLeft[M]): T = fl.foldLeft(x, m.mzero, m.mappend)

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    p(sum(List("aaa","bbb","ccc")))
    // 暗黙的に解決できないパラメタを明示的に指定する
  //p(sum(List(1,2,3,4))(multMonoid))
    p(sum(List(1,2,3,4))(multMonoid, implicitly[FoldLeft[List]]))
    println
  }

}

