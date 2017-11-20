package infrastructure

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

  def getString[T](key: HashedId[T]): Option[String] = {
    memcached.awaitGet[String](key)
  }

  def getJson[A, T](key: HashedId[T])(implicit reads: Reads[A]): Option[A] = {
    getString(key).flatMap(Json.parse(_).asOpt[A])
  }

  def setJson[A, T](key: HashedId[T], value: A, timeout: Duration)(implicit writes: Writes[A]): Unit = {
    memcached.awaitSet(key, Json.toJson(value).toString(), timeout)
  }

  def delete[T](key: HashedId[T]): Unit = {
    memcached.delete(key)
  }
}
