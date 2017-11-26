package repositories

import models.domain.{ AuthUser, User }
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand
import repositories.transaction.Transaction

trait UserRepository {

  def findById(userId: Id[User]): Transaction[Option[User]]

  def findByEmail(email: Email[AuthUser]): Transaction[Option[AuthUser]]

  def create(signUp: SignUpCommand): Transaction[Id[User]]
}
