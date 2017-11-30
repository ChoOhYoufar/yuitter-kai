package models.domain.types

import models.domain.Iso

case class Name[T](value: String) extends AnyVal

object Name {

  implicit def to[T](a: String): Name[T] = Name(a)
  implicit def from(b: Name[_]): String = b.value
  implicit def iso[T]: Iso[String, Name[T]] = Iso(to, from)
}
