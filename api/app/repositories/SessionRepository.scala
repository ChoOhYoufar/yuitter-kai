package repositories

import javax.inject.Inject

import models.views.UserCommand

class SessionRepository @Inject() (
  cache: Cache
) {

  def fetch(key: String): Option[UserCommand] = {
    cache.getJson[UserCommand](key)
  }
//
//  def add(user: User): String = {
//    val key = security.encrypt(user.userId.toString)
//    cache.setJson(key, user, 24 hour)
//    key
//  }
//
//  def delete(user: User): Unit = {
//    val key = security.encrypt(user.userId.toString)
//    cache.delete(key)
//  }
}
