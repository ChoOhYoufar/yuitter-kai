package controllers

import javax.inject.Inject

import play.api.http.Status
import play.api.libs.json.Json
import play.api.mvc.{ Action, AnyContent, Results }
import scenarios.SandBoxScenario

import services.SessionService
import scalaz.std.scalaFuture.futureInstance

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ EitherT, \/ }

class SandBoxController @Inject()(
  sandBoxScenario: SandBoxScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with Status with Results {
//
//  def userFindById(userId: Long): Action[AnyContent] = Action.async { implicit req =>
//    val user = sandBoxScenario.userFindById(userId)
//    Future.successful(user).map(u => Ok(Json.toJson(u)))
//  }

  def sandBox(): Unit = {
    val either1 = EitherT(Future.successful(\/.right(1)))
    val either2 = EitherT(Future.successful(\/.right(2)))
    val eithers = List(either1, either2)
    val acc = EitherT(Future.successful(\/.right(0)))
    eithers.foldLeft(acc) { (acc, either) => acc.flatMap(i => either.map(_ + i)) }
  }
}
