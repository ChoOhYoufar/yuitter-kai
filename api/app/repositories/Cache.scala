package repositories

import play.api.libs.json.{ Reads, Writes }

import scala.concurrent.duration.Duration

trait Cache {

  def getString(key: String): Option[String]

  def getJson[A](key: String)(implicit reads: Reads[A]): Option[A]

  def setJson[A](key: String, value: A, timeout: Duration)(implicit writes: Writes[A]): Unit

  def delete(key: String): Unit
}
