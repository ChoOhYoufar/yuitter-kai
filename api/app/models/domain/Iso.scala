package models.domain

/**
  * TODO: yuito あとでちゃんと調べて使う。いまはまだ難しい
  * 現状はミックスインさせて実装方法を提示するくらいしか意味がない
  * @tparam A 値型
  * @tparam B ラップ型
  */
trait Iso[A, B] {

  def to[T](a: A): B
  def from(b: B): A
}
