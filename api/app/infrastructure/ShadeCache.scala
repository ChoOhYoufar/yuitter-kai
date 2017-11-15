package infrastructure

import javax.inject.{ Inject, Named, Singleton }

import play.api.libs.json.{ Json, Reads, Writes }
import repositories.Cache
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
) extends Cache {

  val memcached = Memcached(Configuration(s"$host:$port"))

  override def getString(key: String): Option[String] = {
    memcached.awaitGet[String](key)
  }

  override def getJson[A](key: String)(implicit reads: Reads[A]): Option[A] = {
    getString(key).flatMap(Json.parse(_).asOpt[A])
  }

  override def setJson[A](key: String, value: A, timeout: Duration)(implicit writes: Writes[A]): Unit = {
    memcached.awaitSet(key, Json.toJson(value).toString(), timeout)
  }

  override def delete(key: String): Unit = {
    memcached.delete(key)
  }
}
