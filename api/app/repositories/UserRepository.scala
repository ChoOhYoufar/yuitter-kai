package repositories

import models.domain.{ AuthUser, HashedAuthInfo, User }
import models.domain.types.{ Email, Id }
import repositories.transaction.Transaction

trait UserRepository {

  def findById(userId: Id[User]): Transaction[Option[User]]

  def findByEmail(email: Email[AuthUser]): Transaction[Option[AuthUser]]

  def create(authInfo: HashedAuthInfo): Transaction[Id[User]]
}
