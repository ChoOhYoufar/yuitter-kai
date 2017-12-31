package controllers

import javax.inject.Inject

import models.views.commands.TweetCommand
import play.api.libs.json.JsValue
import play.api.mvc.Action
import scenarios.TweetScenario
import services.SessionService
import syntax.ToResultOps
import scalaz.std.scalaFuture.futureInstance

import scala.concurrent.{ ExecutionContext, Future }

class TweetController @Inject()(
  tweetScenario: TweetScenario,
  val sessionService: SessionService
)(
  implicit val ec: ExecutionContext
) extends ControllerBase with ToResultOps {

  def create: Action[JsValue] = SecureAction.async(parse.json) { implicit req =>
    (for {
      tweetCreateCommand <- deserializeT[TweetCommand, Future]
      _ <- tweetScenario.create(tweetCreateCommand.toDomain)
    } yield ()).toResult
  }
}
