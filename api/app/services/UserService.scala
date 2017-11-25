package services

import javax.inject.Inject

import models.domain.{ AuthInfo, Errors, User }
import models.domain.types._
import models.views.SignUpCommand
import repositories.{ AbstractDBIO, RDB, SessionRepository, UserRepository }
import syntax.{ DBIOResult, DBResult }

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

  def findById(userId: Id[User]): DBResult[User] = {
    val dbio = userRepository
      .findById(userId)
      .map(userId.checkExists)
    DBResult(dbio)
  }

  def findByAuthInfo(authInfo: AuthInfo): DBResult[User] = {
    val dbio = userRepository
      .findByAuthInfo(authInfo)
      .map(authInfo.checkExists)
    DBResult(dbio)
  }

  def create[F](signUpCommand: SignUpCommand): DBResult[Id[User]] = {
    val dbio: AbstractDBIO[Errors \/ Id[User]] = userRepository.create(signUpCommand).map(\/.right)
    DBResult(dbio)
  }

  def checkExistsEmail(email: Email[User]): DBResult[Unit] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.checkExists)
    DBResult(dbio)
  }
}
