package repositories

import models.domain.{ AuthInfo, User }
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand
import repositories.transaction.Transaction

trait UserRepository {

  def findById(userId: Id[User]): Transaction[Option[User]]

  def findByEmail(email: Email[User]): Transaction[Option[User]]

  def findByAuthInfo(authInfo: AuthInfo): Transaction[Option[User]]

  def create(signUp: SignUpCommand): Transaction[Id[User]]
}
