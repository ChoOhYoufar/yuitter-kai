package repositories

import models.domain.User
import models.domain.types.HashedId

trait SessionRepository {

  def fetch(key: HashedId[User]): Option[User]

  def add(user: User): HashedId[User]

  def delete(user: User): Unit
}
