package repositories

import models.domain.types.HashedId
import play.api.libs.json.{ Reads, Writes }

import scala.concurrent.duration.Duration

trait Cache {

  def getString[T](key: HashedId[T]): Option[String]

  def getJson[A, T](key: HashedId[T])(implicit reads: Reads[A]): Option[A]

  def setJson[A, T](key: HashedId[T], value: A, timeout: Duration)(implicit writes: Writes[A]): Unit

  def delete[T](key: HashedId[T]): Unit
}
