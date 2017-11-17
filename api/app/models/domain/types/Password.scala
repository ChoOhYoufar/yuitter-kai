package models.domain.types

import models.domain.Iso

case class Password[T](value: String) extends AnyVal {

  def isValid: Boolean = value.length > Password.minLength && value.length < Password.maxLength
}

object Password {

  // TODO: yuito 正規表現を追加する
  val maxLength = 60
  val minLength = 15

  implicit def to[T](a: String): Password[T] = Password(a)
  implicit def from(b: Password[_]): String = b.value
  implicit def iso[T]: Iso[String, Password[T]] = Iso(to, from)
}
