package modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import infrastructure.cache.shade.SessionRepositoryCache
import play.api.{ Configuration, Environment }
import repositories.SessionRepository

class ShadeCacheModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  def keyException(key: String) = throw new Exception(s"configure key not found. key=$key")

  val hostKey = "memcached.host"

  val portKey = "memcached.port"

  def configure(): Unit = {
    bind(classOf[String])
      .annotatedWith(Names.named(hostKey))
      .toInstance(
        configuration
          .getString(hostKey)
          .getOrElse(keyException(hostKey))
      )
    bind(classOf[Int])
      .annotatedWith(Names.named(portKey))
      .toInstance(
        configuration
          .getInt(portKey)
          .getOrElse(keyException(portKey))
      )
    bind(classOf[SessionRepository]).to(classOf[SessionRepositoryCache])
  }
}
