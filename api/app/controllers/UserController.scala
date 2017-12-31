package controllers

import javax.inject.Inject

import models.views.formats.UserFormat
import play.api.mvc.{ Action, AnyContent }
import services.SessionService
import syntax.{ Result, ToResultOps }

import scala.concurrent.ExecutionContext

class UserController @Inject()(
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps {

  def me: Action[AnyContent] = SecureAction.async { implicit req =>
    Result(UserFormat.fromDomain(req.user)).toResult
  }
}
