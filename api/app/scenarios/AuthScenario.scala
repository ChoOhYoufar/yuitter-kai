package scenarios

import javax.inject.Inject

import generators.Security
import models.domain.{ AuthInfo, User }
import models.views.SignUpCommand
import play.api.libs.json.JsValue
import play.api.mvc.Request
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ SessionService, UserService }
import syntax.{ DBResult, Result }
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class AuthScenario @Inject()(
  userService: UserService,
  sessionService: SessionService,
  runner: TransactionRunner,
  security: Security
) (
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def signUp(signUpCommand: SignUpCommand): Result[Unit] = {
    val result = for {
      _ <- userService.checkExistsEmail(signUpCommand.email)
      userId <- userService.create(signUpCommand)
      user <- userService.findById(userId)
      _ <- DBResult(sessionService.create(user))
    } yield ()
    runner.exec(result)
  }

  def signIn(authInfo: AuthInfo)(implicit req: Request[JsValue]): Result[Unit] = {
    val result = for {
      _ <- DBResult(sessionService.checkExistsSession)
      authUser <- userService.findByEmail(authInfo.email)
      _ <- DBResult(authInfo.password.authenticate(authUser.password)(security.checkPassword))
      _ <- DBResult(sessionService.create(authUser.user))
    } yield ()
    runner.exec(result)
  }

  def signOut()(implicit ctx: User): Result[Unit] = {
    Result(sessionService.delete())
  }
}
