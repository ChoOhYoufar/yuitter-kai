package infrastructure.jdbc.slick

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import generators.Security
import infrastructure.jdbc.slick.tables.models.RichDBModels
import infrastructure.jdbc.slick.transaction.SlickTransaction
import repositories.UserRepository
import slick.driver.MySQLDriver.api._
import utils.Constants

import scala.concurrent.ExecutionContext
import infrastructure.jdbc.slick.tables.models.Tables._
import models.domain.{ AuthUser, HashedAuthInfo, User }
import models.domain.types.{ Email, Id, Status }

class UserRepositorySlick @Inject()(
  security: Security
) (
  implicit ec: ExecutionContext
) extends UserRepository with RichDBModels {

  override def findById(userId: Id[User]): SlickTransaction[Option[User]] = {
    val dbio = Users
      .filter(_.userId === userId.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
    SlickTransaction(dbio)
  }

  override def findByEmail(email: Email[AuthUser]): SlickTransaction[Option[AuthUser]] = {
    val dbio = Users
      .filter(_.email === email.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomainAuthUser))
    SlickTransaction(dbio)
  }

  override def create(authInfo: HashedAuthInfo): SlickTransaction[Id[User]] = {
    val dbio = Users returning Users.map(_.userId) += UsersRow(
      userId = Constants.DefaultId,
      email = authInfo.email,
      password = authInfo.hashedPassword,
      userStatus = Status.Enable.value,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now),
      updateDatetime = Timestamp.valueOf(LocalDateTime.now),
      versionNo = Constants.DefaultVersionNo
    )
    SlickTransaction(dbio.map(Id(_)))
  }
}
