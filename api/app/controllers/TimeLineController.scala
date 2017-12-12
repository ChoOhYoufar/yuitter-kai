package controllers

import javax.inject.Inject

import models.views.TimeLineFormat
import play.api.mvc.{ Action, AnyContent }
import scenarios.TimeLineScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext
import scalaz.std.FutureInstances

class TimeLineController @Inject()(
  timeLineScenario: TimeLineScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with FutureInstances {

  def find(accountId: Long): Action[AnyContent] = SecureAction.async { implicit req =>
    timeLineScenario.list(accountId).map(TimeLineFormat.fromDomain).toResult
  }
}
