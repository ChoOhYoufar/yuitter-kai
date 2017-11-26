package services

import javax.inject.Inject

import models.domain.{ AuthInfo, User }
import models.domain.types._
import models.views.SignUpCommand
import repositories.{ SessionRepository, UserRepository }
import syntax.DBResult

import scala.concurrent.ExecutionContext
import scalaz.\/
import scalaz.syntax.std.ToOptionOps

class UserService @Inject()(
  userRepository: UserRepository,
  sessionRepository: SessionRepository
)(
  implicit ec: ExecutionContext
) extends ToOptionOps {

  def findById(userId: Id[User]): DBResult[User] = {
    val dbio = userRepository
      .findById(userId)
      .map(userId.assertExists)
    DBResult(dbio)
  }

  def findByEmail(email: Email[User]): DBResult[User] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.assertExists)
    DBResult(dbio)
  }

  def create(signUpCommand: SignUpCommand): DBResult[Id[User]] = {
    val dbio = userRepository.create(signUpCommand).map(\/.right)
    DBResult(dbio)
  }

  def checkExistsEmail(email: Email[User]): DBResult[Unit] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.assertNone)
    DBResult(dbio)
  }
}
