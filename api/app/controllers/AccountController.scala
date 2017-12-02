package controllers

import javax.inject.Inject

import models.views.AccountCreateCommand
import play.api.libs.json.JsValue
import play.api.mvc.Action
import scenarios.AccountScenario
import services.SessionService
import syntax.ToResultOps

import scalaz.std.scalaFuture.futureInstance
import scala.concurrent.{ ExecutionContext, Future }

class AccountController @Inject()(
  accountScenario: AccountScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps {

  def create: Action[JsValue] = SecureAction.async(parse.json) { implicit req =>
    (for {
      accountCreateCommand <- deserializeT[AccountCreateCommand, Future]
      _ <- accountScenario.create(accountCreateCommand.toDomain)
    } yield ()).toResult
  }
}
