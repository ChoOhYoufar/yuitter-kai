package controllers

import javax.inject.Inject

import models.views.TimelineFormat
import play.api.mvc.{ Action, AnyContent }
import scenarios.TimelineScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext
import scalaz.std.FutureInstances

class TimelineController @Inject()(
  timelineScenario: TimelineScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with FutureInstances {

  def find(accountId: Long): Action[AnyContent] = SecureAction.async { implicit req =>
    timelineScenario.find(accountId).map(TimelineFormat.fromDomain).toResult
  }
}
