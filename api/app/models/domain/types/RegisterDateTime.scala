package models.domain.types

import java.time.LocalDateTime

import models.domain.Iso

case class RegisterDateTime[T](value: LocalDateTime)  extends AnyVal

object RegisterDateTime {

  implicit def to[T](a: LocalDateTime): RegisterDateTime[T] = RegisterDateTime(a)
  implicit def from(b: RegisterDateTime[_]): LocalDateTime = b.value
  implicit def iso[T]: Iso[LocalDateTime, RegisterDateTime[T]] = Iso(to, from)
}
