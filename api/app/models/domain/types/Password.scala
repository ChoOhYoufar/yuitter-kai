package models.domain.types

import models.domain.{ Errors, Iso }

import scalaz.\/

case class Password[T](value: String) extends AnyVal {

  def isValid: Boolean = value.length >= Password.minLength && value.length <= Password.maxLength

  def authenticate(hashed: HashedPassword[T])(checkPassword: (String, String) => Boolean): Errors \/ Unit = {
    if (checkPassword(this, hashed)) {
      \/.right(())
    }  else {
      \/.left(Errors.InvalidPassword)
    }
  }

  def hash(encrypt: String => String): HashedPassword[T] = {
    val hashedPassword: HashedPassword[T] = encrypt(value)
    if (hashedPassword.isValid) {
      hashedPassword
    } else {
      throw new Exception("Hash algorithm might be wrong.")
    }
  }
}

object Password {

  // TODO: yuito 正規表現を追加する
  val maxLength = 60
  val minLength = 8

  implicit def to[T](a: String): Password[T] = Password(a)
  implicit def from(b: Password[_]): String = b.value
  implicit def iso[T]: Iso[String, Password[T]] = Iso(to, from)
}
