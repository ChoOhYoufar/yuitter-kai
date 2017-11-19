package infrastructure

import java.sql.Timestamp
import java.time.LocalDateTime

import models.domain.User
import models.domain.types.{ Email, Id }
import repositories.UserRepository
import slick.dbio.DBIO
import slick.driver.MySQLDriver.api._
import models.db.Tables._
import models.db._
import models.views.SignUpCommand
import utils.Constants

import scala.concurrent.ExecutionContext

class UserRepositoryJDBC(
  implicit ec: ExecutionContext
) extends UserRepository with RichDBModels {

  override def findById(userId: Id[User]): DBIO[Option[User]] = {
    Users
      .filter(_.userId === userId.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
  }

  override def findByEmail(email: Email[User]): DBIO[Option[User]] = {
    Users
      .filter(_.email === email.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
  }

  override def create(signUp: SignUpCommand): DBIO[Id[User]] = {
    val dbio = Users returning Users.map(_.userId) += UsersRow(
      userId = Constants.DefaultId,
      email = signUp.email,
      password = signUp.password,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now),
      updateDatetime = Timestamp.valueOf(LocalDateTime.now),
      versionNo = Constants.DefaultVersionNo
    )
    dbio.map(Id(_))
  }
}
