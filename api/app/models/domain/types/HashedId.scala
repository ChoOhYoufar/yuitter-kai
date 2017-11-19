package models.domain.types

import scalaz.syntax.std.ToOptionOps

case class HashedId[T](value: String) extends AnyVal with Hashed

object HashedId extends ToOptionOps {

  implicit def to[T](a: String): HashedId[T] = HashedId(a)
  implicit def from(b: HashedId[_]): String = b.value
}
