package controllers

import javax.inject.Inject

import models.domain.Account
import models.domain.types.Id
import models.views.{ AccountCreateCommand, AccountFormat, AccountUpdateCommand }
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
      accountCreateCommand <- deserializeT[AccountCreateCommand, Future]
      _ <- accountScenario.create(accountCreateCommand.toDomain)
    } yield ()).toResult
  }

  def update: Action[JsValue] = SecureAction.async(parse.json) { implicit req =>
    (for {
      accountUpdateCommand <- deserializeT[AccountUpdateCommand, Future]
      _ <- accountScenario.update(accountUpdateCommand.toDomain)
    } yield()).toResult
  }

  def find(accountId: Id[Account]): Action[AnyContent] = SecureAction.async { implicit req =>
    accountScenario.find(accountId).map(AccountFormat.fromDomain).toResult
  }
}
