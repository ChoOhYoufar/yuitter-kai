package controllers

import javax.inject.Inject

import models.domain.Account
import models.domain.types.Id
import play.api.mvc.{ Action, AnyContent }
import scenarios.TimeLineScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext

class TimeLineController @Inject()(
  timeLineScenario: TimeLineScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps {

  def list(accountId: Id[Account]): Action[AnyContent] = SecureAction.async { implicit req =>
    timeLineScenario.list(accountId).toResult
  }
}
