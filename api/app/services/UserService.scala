package services

import javax.inject.Inject

import models.domain.{ AuthInfo, User }
import models.domain.types._
import models.views.SignUpCommand
import repositories.{ RDB, SessionRepository, UserRepository }
import syntax.DBIOResult

import scala.concurrent.ExecutionContext
import scalaz.\/
import scalaz.syntax.std.ToOptionOps

class UserService @Inject()(
  userRepository: UserRepository,
  sessionRepository: SessionRepository,
  rdb: RDB
)(
  implicit ec: ExecutionContext
) extends ToOptionOps {

  def findById(userId: Id[User]): DBIOResult[User] = {
    val dbio = userRepository
      .findById(userId)
      .map(userId.checkExists)
    DBIOResult(dbio)
  }

  def findByAuthInfo(authInfo: AuthInfo): DBIOResult[User] = {
    val dbio = userRepository
      .findByAuthInfo(authInfo)
      .map(authInfo.checkExists)
    DBIOResult(dbio)
  }

  def create(signUpCommand: SignUpCommand): DBIOResult[Id[User]] = {
    val dbio = userRepository.create(signUpCommand).map(\/.right)
    DBIOResult(dbio)
  }

  def checkExistsEmail(email: Email[User]): DBIOResult[Unit] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.checkExists)
    DBIOResult(dbio)
  }
}
