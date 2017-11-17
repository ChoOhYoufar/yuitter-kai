package models.domain.types

case class Id[T](value: Long) extends AnyVal { }

object Id {

  implicit def to[T](a: Long): Id[T] = Id(a)
  implicit def from(b: Id[_]): Long = b.value
}
