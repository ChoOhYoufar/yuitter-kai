package repositories

import javax.inject.Inject

import generators.Security
import models.domain.User
import models.domain.types.HashedId
import models.views.{ UserCommand, UserFormat }

import scala.concurrent.duration._
import scala.language.postfixOps

class SessionRepository @Inject() (
  cache: Cache,
  security: Security
) {

  def fetch(key: HashedId[User]): Option[User] = {
    cache.getJson[UserCommand, User](key).map(_.toDomain)
  }

  def add(user: User): Unit = {
    val key = user.userId.hash(security.encrypt)
    cache.setJson(key, UserFormat.fromDomain(user), 24 hour)
  }

  def delete(user: User): Unit = {
    val key = user.userId.hash(security.encrypt)
    cache.delete(key)
  }
}
