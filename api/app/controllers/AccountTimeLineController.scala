package controllers

import javax.inject.Inject

import models.views.TimelineFormat
import play.api.mvc.{ Action, AnyContent }
import scenarios.AccountTimelineScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext
import scalaz.std.FutureInstances

class AccountTimelineController @Inject()(
  accountTimelineScenario: AccountTimelineScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with FutureInstances {

  def find(accountId: Long): Action[AnyContent] = SecureAction.async { implicit req =>
    accountTimelineScenario
      .find(accountId)
      .map(TimelineFormat.fromDomain)
      .toResult
  }
}
