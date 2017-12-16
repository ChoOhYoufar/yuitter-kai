package models.domain

import play.api.Logger


trait EnumCompanion[A, B <: Enum[A]] {
  val logger = Logger(this.getClass)
  def values: Seq[B]
  def valueOf(value: A): B = values
    .find(_.value == value)
    // NOTE yuito: DBやCacheに保存されているEnum値が不正な値なときに起きる。
    // ユーザーは自己解決できないエラーのためEitherで返すことはせず、エラーを投げる。
    .getOrElse({
      logger.error(s"$value is invalid for Enum.")
      throw new Exception(s"$value is invalid for Enum.")
    })
}
