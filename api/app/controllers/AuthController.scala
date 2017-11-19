package controllers

import javax.inject.Inject

import models.views.SignUpCommand
import play.api.libs.json.JsValue
import play.api.mvc.Action
import services.AuthService
import syntax.ResultOps
import scalaz.std.scalaFuture.futureInstance

import scala.concurrent.{ ExecutionContext, Future }

class AuthController @Inject() (
  authService: AuthService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ResultOps {

  def signUp: Action[JsValue] = Action.async(parse.json) { implicit req =>
    (for {
      signUpCommand <- deserializeT[SignUpCommand, Future]
      _ <- authService.signUp(signUpCommand)
    } yield ()).toResult
  }
}
