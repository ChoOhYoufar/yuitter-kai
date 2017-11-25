package repositories

import models.domain.{ AuthInfo, User }
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand

trait UserRepository {

  def findById(userId: Id[User]): AbstractDBIO[Option[User]]

  def findByEmail(email: Email[User]): AbstractDBIO[Option[User]]

  def findByAuthInfo(authInfo: AuthInfo): AbstractDBIO[Option[User]]

  def create(signUp: SignUpCommand): AbstractDBIO[Id[User]]
}
