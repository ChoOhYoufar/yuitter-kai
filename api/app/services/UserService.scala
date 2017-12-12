package services

import javax.inject.Inject

import models.domain.{ AuthUser, HashedAuthInfo, User }
import models.domain.types._
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
      .map(userId.assertExist)
    DBResult(dbio)
  }

  def findByEmail(email: Email[AuthUser]): DBResult[AuthUser] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.asInstanceOf[Email[AuthUser]].assertExist)
    DBResult(dbio)
  }

  def create(authInfo: HashedAuthInfo): DBResult[Id[User]] = {
    val dbio = userRepository.create(authInfo).map(\/.right)
    DBResult(dbio)
  }

  def checkExistsEmail(email: Email[AuthUser]): DBResult[Unit] = {
    val dbio = userRepository
      .findByEmail(email)
      .map(email.assertNone)
    DBResult(dbio)
  }
}
