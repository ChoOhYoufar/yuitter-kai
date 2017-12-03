package controllers

import javax.inject.Inject

import generators.Security
import models.views.{ SignInCommand, SignUpCommand }
import play.api.libs.json.JsValue
import play.api.mvc.Action
import scenarios.AuthScenario
import services.SessionService
import syntax.ToResultOps

import scalaz.std.scalaFuture.futureInstance
import scala.concurrent.{ ExecutionContext, Future }
import scalaz.syntax.std.ToOptionOps

class AuthController @Inject() (
  authScenario: AuthScenario,
  security: Security,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with ToOptionOps {

  def signUp: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      signUpCommand <- deserializeT[SignUpCommand, Future]
      hashedUserId <- authScenario.signUp(signUpCommand.toDomain(security.encrypt))
    } yield hashedUserId).toResult { id =>
      Ok.withSession("session" -> id.value)
    }
  }

  def signIn: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      signInCommand <- deserializeT[SignInCommand, Future]
      hashedUserId <- authScenario.signIn(signInCommand.toDomain)
    } yield hashedUserId).toResult { id =>
      Ok.withSession("session" -> id.value)
    }
  }

  def signOut: Action[JsValue] = SecureAction.async(parse.json) { implicit req =>
    authScenario.signOut().toResult(_ => Ok.withNewSession)
  }
}
