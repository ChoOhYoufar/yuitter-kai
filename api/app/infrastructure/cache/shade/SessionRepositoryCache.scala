package infrastructure.cache.shade

import javax.inject.Inject

import generators.Security
import models.domain.User
import models.domain.types.Id
import models.views.commands.UserCommand
import models.views.formats.UserFormat
import repositories.SessionRepository

import scala.concurrent.duration._
import scala.language.postfixOps

class SessionRepositoryCache @Inject() (
  cache: ShadeCache,
  security: Security
) extends SessionRepository {

  override def fetch(userId: Id[User]): Option[User] = {
    cache.getJson[UserCommand](userId.value.toString).map(_.toDomain)
  }

  override def add(user: User): Id[User] = {
    cache.setJson(user.userId.value.toString, UserFormat.fromDomain(user), 24 hour)
    user.userId
  }

  override def delete(user: User): Unit = {
    val key = user.userId.hash(security.encrypt)
    cache.delete(key)
  }
}
