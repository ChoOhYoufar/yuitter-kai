package repositories

import models.domain.User
import models.domain.types.Id

trait SessionRepository {

  def fetch(userId: Id[User]): Option[User]

  def add(user: User): Id[User]

  def delete(user: User): Unit
}
