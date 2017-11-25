package infrastructure

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import generators.Security
import models.domain.{ AuthInfo, User }
import models.domain.types.{ Email, Id }
import repositories.UserRepository
import slick.dbio.DBIO
import slick.driver.MySQLDriver.api._
import models.db.Tables._
import models.db._
import models.views.SignUpCommand
import utils.Constants

import scala.concurrent.ExecutionContext

class UserRepositorySlick @Inject()(
  security: Security
) (
  implicit ec: ExecutionContext
) extends UserRepository with RichDBModels {

  override def findById(userId: Id[User]): SlickDBIO[Option[User]] = {
    val dbio :DBIO[Option[User]] = Users
      .filter(_.userId === userId.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))

    SlickDBIO(dbio)
  }

  override def findByEmail(email: Email[User]): SlickDBIO[Option[User]] = {
    val dbio = Users
      .filter(_.email === email.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
    SlickDBIO(dbio)
  }

  override def findByAuthInfo(authInfo: AuthInfo): SlickDBIO[Option[User]] = {
    val dbio = Users
      .filter(_.email === authInfo.email.value.bind)
      .filter(_.password === authInfo.password.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
    SlickDBIO(dbio)
  }

  override def create(signUp: SignUpCommand): SlickDBIO[Id[User]] = {
    val dbio = Users returning Users.map(_.userId) += UsersRow(
      userId = Constants.DefaultId,
      email = signUp.email,
      password = signUp.password.hash(security.encrypt),
      registerDatetime = Timestamp.valueOf(LocalDateTime.now),
      updateDatetime = Timestamp.valueOf(LocalDateTime.now),
      versionNo = Constants.DefaultVersionNo
    )
    SlickDBIO(dbio.map(Id(_)))
  }
}
