package models.domain.types

import models.domain.{ Errors, Iso }

import scalaz.\/
import scalaz.syntax.std.ToOptionOps

case class Id[T](value: Long) extends ToOptionOps {

  /**
    * idでDBを検索したときに存在しなかったらエラーを返すメソッド
    * @param result DB接続の結果
    */
  def checkExists(result: Option[T]): Errors \/ T = {
    result \/> Errors.IdNotFound(this)
  }

  def hash(encrypt: String => String): HashedId[T] = {
    val hashedId: HashedId[T] = encrypt(value.toString)
    if (hashedId.isValid) {
      hashedId
    } else {
      throw new Exception("Hash algorithm might be wrong.")
    }
  }
}

object Id {

  implicit def to[T](a: Long): Id[T] = Id(a)
  implicit def from(b: Id[_]): Long = b.value
  implicit def iso[T]: Iso[Long, Id[T]] = Iso(to, from)
}
