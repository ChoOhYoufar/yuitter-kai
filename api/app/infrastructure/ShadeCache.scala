package infrastructure

import javax.inject.{ Inject, Named, Singleton }

import play.api.libs.json.{ Json, Reads }
import repositories.Cache
import shade.memcached._

// NOTE memchacedクライアントのurl→https://github.com/monix/shade
// NOTE ShadeCacheはシングルトンで運用する
@Singleton
class ShadeCache @Inject() (
  @Named("memcached.host") host: String,
  @Named("memcached.port") port: Int
) extends Cache {

  val memcached = Memcached(Configuration(s"$host:$port"))

  override def getString(key: String): Option[String] = {
    memcached.awaitGet(key)
  }

  override def getJson[A](key: String)(implicit reads: Reads[A]): Option[A] = {
    getString(key).flatMap(Json.parse(_).asOpt[A])
  }
}
