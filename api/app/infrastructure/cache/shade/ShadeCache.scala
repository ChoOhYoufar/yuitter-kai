package infrastructure.cache.shade

import javax.inject.{ Inject, Named, Singleton }

import models.domain.types.HashedId
import play.api.libs.json.{ Json, Reads, Writes }
import shade.memcached._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

// NOTE memchacedクライアントのurl→https://github.com/monix/shade
// NOTE ShadeCacheはシングルトンで運用する
@Singleton
class ShadeCache @Inject() (
  @Named("memcached.host") host: String,
  @Named("memcached.port") port: Int
) (
  implicit ec: ExecutionContext
) {

  val memcached = Memcached(Configuration(s"$host:$port"))

  def getString(key: String): Option[String] = {
    memcached.awaitGet[String](key)
  }

  def getJson[A](key: String)(implicit reads: Reads[A]): Option[A] = {
    getString(key).flatMap(Json.parse(_).asOpt[A])
  }

  def setJson[A](key: String, value: A, timeout: Duration)(implicit writes: Writes[A]): Unit = {
    memcached.awaitSet(key, Json.toJson(value).toString(), timeout)
  }

  def delete(key: String): Unit = {
    memcached.delete(key)
  }
}
