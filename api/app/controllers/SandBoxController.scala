package controllers

import javax.inject.Inject

import play.api.http.Status
import play.api.libs.json.Json
import play.api.mvc.{ Action, AnyContent, Results }
import scenarios.SandBoxScenario
import services.SessionService

import scala.concurrent.Future

class SandBoxController @Inject()(
  sandBoxScenario: SandBoxScenario,
  val sessionService: SessionService
)(
  implicit val ec: SessionService
) extends ControllerBase with Status with Results {

  def userFindById(userId: Long): Action[AnyContent] = Action.async { implicit req =>
    val user = sandBoxScenario.userFindById(userId)
    Future.successful(user).map(u => Ok(Json.toJson(u)))
  }
}
