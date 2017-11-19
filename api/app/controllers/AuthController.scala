package controllers

import javax.inject.Inject

import generators.Security
import models.views.{ SignInCommand, SignUpCommand }
import play.api.libs.json.JsValue
import play.api.mvc.Action
import scenarios.AuthScenario
import syntax.ToResultOps

import scalaz.std.scalaFuture.futureInstance
import scala.concurrent.{ ExecutionContext, Future }
import scalaz.syntax.std.ToOptionOps

class AuthController @Inject() (
  authScenario: AuthScenario,
  security: Security
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with ToOptionOps {

  def signUp: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      signUpCommand <- deserializeT[SignUpCommand, Future]
      _ <- authScenario.signUp(signUpCommand)
    } yield ()).toResult
  }

  def signIn: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      signInCommand <- deserializeT[SignInCommand, Future]
      _ <- authScenario.signIn(signInCommand.toDomain(security.encrypt))
    } yield ()).toResult
  }
}
