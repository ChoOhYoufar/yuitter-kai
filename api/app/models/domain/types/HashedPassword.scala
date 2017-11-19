package models.domain.types

case class HashedPassword[T](value: String) extends Hashed

object HashedPassword {

  implicit def to[T](a: String): HashedPassword[T] = HashedPassword(a)
  implicit def from(b: HashedPassword[_]): String = b.value
}
