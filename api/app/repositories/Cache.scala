package repositories

import play.api.libs.json.Reads

trait Cache {

  def getString(key: String): Option[String]

  def getJson[A](key: String)(implicit reads: Reads[A]): Option[A]
}
