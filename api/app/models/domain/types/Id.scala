package models.domain.types

import models.domain.Iso

case class Id[T](value: Long) extends AnyVal { }

object Id {

  implicit def to[T](a: Long): Id[T] = Id(a)
  implicit def from(b: Id[_]): Long = b.value
  implicit def iso[T]: Iso[Long, Id[T]] = Iso(to, from)
}
