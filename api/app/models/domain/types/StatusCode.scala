package models.domain.types

import models.domain.Iso

case class StatusCode[T](value: String) extends AnyVal {

  def isValid: Boolean = value.length == StatusCode.length
}


object StatusCode {

  val length = 3

  implicit def to[T](a: String): StatusCode[T] = StatusCode(a)
  implicit def from(b: StatusCode[_]): String = b.value
  implicit def iso[T]: Iso[String, StatusCode[T]] = Iso(to, from)
}
