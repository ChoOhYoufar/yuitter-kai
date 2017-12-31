package controllers

import javax.inject.Inject

import models.views.commands.{ AccountCommand, AccountUpdateCommand }
import models.views.formats.AccountFormat
import play.api.libs.json.JsValue
import play.api.mvc.{ Action, AnyContent }
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
      accountCommand <- deserializeT[AccountCommand, Future]
      _ <- accountScenario.create(accountCommand.toDomain)
    } yield ()).toResult
  }

  def update: Action[JsValue] = SecureAction.async(parse.json) { implicit req =>
    (for {
      accountCommand <- deserializeT[AccountCommand, Future]
      _ <- accountScenario.update(accountCommand.toDomain)
    } yield()).toResult
  }

  def find(accountId: Long): Action[AnyContent] = SecureAction.async { implicit req =>
    accountScenario.find(accountId).map(AccountFormat.fromDomain).toResult
  }
}
