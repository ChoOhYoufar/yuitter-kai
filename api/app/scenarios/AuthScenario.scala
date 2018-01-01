package scenarios

import javax.inject.Inject

import generators.Security
import models.domain.types.{ HashedId, Id }
import models.domain.{ AuthInfo, HashedAuthInfo, User }
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

  def signUp(authInfo: HashedAuthInfo): Result[Id[User]] = {
    val result = for {
      _ <- userService.checkExistsEmail(authInfo.email)
      userId <- userService.create(authInfo)
      user <- userService.findById(userId)
      userId <- DBResult(sessionService.create(user))
    } yield userId
    runner.exec(result)
  }

  def signIn(authInfo: AuthInfo)(implicit req: Request[JsValue]): Result[Id[User]] = {
    val result = for {
      _ <- DBResult(sessionService.checkExistsSession)
      authUser <- userService.findByEmail(authInfo.email)
      _ <- DBResult(authInfo.password.authenticate(authUser.password)(security.checkPassword))
      userId <- DBResult(sessionService.create(authUser.user))
    } yield userId
    runner.exec(result)
  }

  def signOut()(implicit ctx: User): Result[Unit] = {
    Result(sessionService.delete)
  }
}
