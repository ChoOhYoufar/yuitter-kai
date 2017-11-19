package services

import javax.inject.Inject

import infrastructure.SlickDB
import models.domain.{ Errors, User }
import models.domain.types.{ Email, Id }
import models.views.SignUpCommand
import repositories.UserRepository
import slick.dbio.DBIO
import syntax.{ DBIOInstances, DBIOResult, Result, ToEitherOps }

import scalaz.syntax.std.ToOptionOps
import scalaz.\/
import scala.concurrent.ExecutionContext

class AuthService @Inject()(
  userRepository: UserRepository,
  slickDB: SlickDB
)(
  implicit val ec: ExecutionContext
) extends ToOptionOps with ToEitherOps with DBIOInstances {


  def signUp(signUpCommand: SignUpCommand): Result[Id[User]] = {
    DBIOResult("").map(_ + "")
    val result: DBIOResult[Id[User]] = for {
      _ <- checkExistsEmail(signUpCommand.email)
      id <- DBIOResult(userRepository.create(signUpCommand).map(\/.right))
    } yield id
    slickDB.exec(result)
  }

  private def checkExistsEmail(email: Email[User]): DBIOResult[Unit] = {
    val dbio: DBIO[Errors \/ Unit] = userRepository
      .findByEmail(email)
      .map(_ ?
        \/.left[Errors, Unit](Errors.EmailExistsError(email)) |
        \/.right[Errors, Unit](())
      )
    DBIOResult(dbio)
  }
}
