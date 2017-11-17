package models.domain.types


case class Email[T](value: String)

object Email extends {

  implicit def to[T](a: String): Email[T] = Email(a)
  implicit def from(b: Email[_]): String = b.value
}
