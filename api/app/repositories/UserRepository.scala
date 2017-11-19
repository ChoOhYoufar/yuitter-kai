package repositories

import models.domain.User
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand
import slick.dbio.DBIO

trait UserRepository {

  def findById(userId: Id[User]): DBIO[Option[User]]

  def findByEmail(email: Email[User]): DBIO[Option[User]]

  def create(signUp: SignUpCommand): DBIO[Id[User]]
}