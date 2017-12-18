package infrastructure.cache.shade

import javax.inject.Inject

import generators.Security
import models.domain.User
import models.domain.types.HashedId
import models.views.commands.UserCommand
import models.views.formats.UserFormat
import repositories.SessionRepository

import scala.concurrent.duration._
import scala.language.postfixOps

class SessionRepositoryCache @Inject() (
  cache: ShadeCache,
  security: Security
) extends SessionRepository {

  override def fetch(key: HashedId[User]): Option[User] = {
    cache.getJson[UserCommand, User](key).map(_.toDomain)
  }

  override def add(user: User): HashedId[User] = {
    val key = user.userId.hash(security.encrypt)
    cache.setJson(key, UserFormat.fromDomain(user), 24 hour)
    key
  }

  override def delete(user: User): Unit = {
    val key = user.userId.hash(security.encrypt)
    cache.delete(key)
  }
}
