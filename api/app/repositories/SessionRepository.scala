package repositories

import javax.inject.Inject

import generators.Security
import models.domain.User
import models.views.{ UserCommand, UserFormat }

import scala.concurrent.duration._
import scala.language.postfixOps

class SessionRepository @Inject() (
  cache: Cache,
  security: Security
) {

  def fetch(key: String): Option[UserCommand] = {
    cache.getJson[UserCommand](key)
  }

  def add(user: User): String = {
    val key = security.encrypt(user.userId.toString)
    cache.setJson(key, UserFormat.fromDomain(user), 24 hour)
    key
  }

  def delete(user: User): Unit = {
    val key = security.encrypt(user.userId.toString)
    cache.delete(key)
  }
}
