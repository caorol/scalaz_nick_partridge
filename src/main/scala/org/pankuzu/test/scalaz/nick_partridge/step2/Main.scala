package org.pankuzu.test.scalaz.nick_partridge.step2

/**
  * https://gist.github.com/takuya71/3760513
  *
  * step 2
  * -> Monoid を導入する
  *    Monoid: 『数学、とくに抽象代数学における単系（たんけい、英: monoid; モノイド）はひとつの二項演算と単位元をもつ代数的構造である』
  *            加法の二項演算子は + で単位元が 0
  *            乗法の二項演算子は * で単位元は 1
  *            そして、加法と乗法が結合則を満たしているのは明らか。
  *            このように、二項演算子が結合則を満たし、かつ単位元が存在するような構造を「モノイド (Monoid) 」といいます。
  *   -> 結合則 どの順番で計算しても同じ答えになること
  *   -> 単位元 指定した数値と単位元を計算（加法なら+、乗法なら*）しても元の数値が変わらない数
  * -> x.foldLeft(0){(a, b) => a + b} のところに導入できる
  */
object Main {
  def sum(x: List[Int]): Int = x.foldLeft(IntMonoid.mzero){IntMonoid.mappend}

  def p(a: Any) {println("###> " + a)}
  def main(args: Array[String]): Unit = {
    println
    p(sum(List(1,2,3,4)))
    println
  }

}

object IntMonoid {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0
}