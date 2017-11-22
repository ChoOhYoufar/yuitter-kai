package repositories

import models.domain.{ AuthInfo, User }
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand
import slick.dbio.DBIO

trait UserRepository {

  def findById(userId: Id[User]): AbstractDBIO[Option[User]]

  def findByEmail(email: Email[User]): DBIO[Option[User]]

  def findByAuthInfo(authInfo: AuthInfo): DBIO[Option[User]]

  def create(signUp: SignUpCommand): DBIO[Id[User]]
}
