package controllers

import javax.inject.Inject

import play.api.libs.json.JsValue
import play.api.mvc.Action
import scenarios.AccountFollowingScenario
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.ExecutionContext
import scalaz.std.FutureInstances

class AccountFollowingController @Inject()(
  accountFollowingScenario: AccountFollowingScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps with FutureInstances {}
