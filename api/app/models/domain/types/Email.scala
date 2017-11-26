package models.domain.types

import models.domain.{ Errors, Iso }

import scalaz.\/
import scalaz.syntax.std.ToOptionOps

case class Email[T](value: String) extends ToOptionOps {

  def isValid: Boolean = value.matches(Email.pattern) && value.length < Email.maxLength

  /**
    * emailでDBを検索したときに存在したらエラーを返すメソッド
    * @param searchResult DB接続の結果
    */
  def assertNone(searchResult: Option[T]): Errors \/ Unit = {
    searchResult ?
      \/.left[Errors, Unit](Errors.EmailExistsError(this)) |
      \/.right[Errors, Unit](())
  }

  /**
    * emailでDBを検索したときに存在しなかったらエラーを返すメソッド
    * @param searchResult DB接続の結果
    */
  def assertExists(searchResult: Option[T]): Errors \/ T = {
    searchResult \/> Errors.EmailNotFound(this)
  }
}

object Email extends {

  val pattern = """^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"""
  val maxLength = 150

  implicit def to[T](a: String): Email[T] = Email(a)
  implicit def from(b: Email[_]): String = b.value
  implicit def iso[T]: Iso[String, Email[T]] = Iso(to, from)
}
