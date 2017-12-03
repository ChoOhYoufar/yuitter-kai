package models.domain.types

import models.domain.Iso

case class Text[T](value: String) extends AnyVal {

  def isValid: Boolean = value.length <= Text.maxLength
}

object Text {

  val maxLength = 140

  implicit def to[T](a: String): Text[T] = Text(a)
  implicit def from(b: Text[_]): String = b.value
  implicit def iso[T]: Iso[String, Text[T]] = Iso(to, from)
}
