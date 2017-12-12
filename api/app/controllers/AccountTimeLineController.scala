package controllers

import javax.inject.Inject

import models.views.TimeLineFormat
import play.api.mvc.{ Action, AnyContent }
import scenarios.AccountTimeLineScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext
import scalaz.std.FutureInstances

class AccountTimeLineController @Inject()(
  accountTimeLineScenario: AccountTimeLineScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with FutureInstances {

  def find(accountId: Long): Action[AnyContent] = SecureAction.async { implicit req =>
    accountTimeLineScenario
      .find(accountId)
      .map(TimeLineFormat.fromDomain)
      .toResult
  }
}
