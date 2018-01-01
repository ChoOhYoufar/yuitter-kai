package controllers

import javax.inject.Inject

import generators.Security
import models.views.commands.AuthInfoCommand
import play.api.libs.json.JsValue
import play.api.mvc.{ Action, AnyContent }
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
      signUpCommand <- deserializeT[AuthInfoCommand, Future]
      hashedUserId <- authScenario.signUp(signUpCommand.toDomainHashedAuthInfo(security.encrypt))
    } yield hashedUserId).toResult { id =>
      Ok.withSession("session" -> id.value.toString)
    }
  }

  def signIn: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      authInfoCommand <- deserializeT[AuthInfoCommand, Future]
      hashedUserId <- authScenario.signIn(authInfoCommand.toDomainAuthInfo)
    } yield hashedUserId).toResult { id =>
      Ok.withSession("session" -> id.value.toString)
    }
  }

  def signOut: Action[AnyContent] = SecureAction.async { implicit req =>
    authScenario.signOut().toResult(_ => Ok.withNewSession)
  }
}
