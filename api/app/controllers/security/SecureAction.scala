package controllers.security

import javax.inject.Inject

import models.domain.{ Errors, User }
import play.api.mvc._
import services.SessionService
import syntax.ToResultOps

import scala.concurrent.Future

class SecureAction @Inject() (
  sessionService: SessionService
) extends ToResultOps {

  def findUserBySession(req: Request[_]): Option[User] = {
    for {
      key <- req.session.get("session")
      user <- sessionService.findBy(key.toLong)
    } yield user
  }

  def apply(requestHandler: SecureRequest[AnyContent] => Result): Action[AnyContent] = {
    apply(BodyParsers.parse.anyContent)(requestHandler)
  }

  def apply[A](bodyParser: BodyParser[A])(requestHandler: SecureRequest[A] => Result): Action[A] = {
    Action(bodyParser) { req =>
      findUserBySession(req) match {
        case Some(user) => requestHandler(SecureRequest(user, req))
        case _ => Errors.Unauthorized.toResult
      }
    }
  }

  def async(requestHandler: SecureRequest[AnyContent] => Future[Result]): Action[AnyContent] = {
    async(BodyParsers.parse.anyContent)(requestHandler)
  }

  def async[A](bodyParser: BodyParser[A])(requestHandler: SecureRequest[A] => Future[Result]): Action[A] = {
    Action.async(bodyParser) { req =>
      findUserBySession(req) match {
        case Some(user) => requestHandler(SecureRequest(user, req))
        case _ => Future.successful(Errors.Unauthorized.toResult)
      }
    }
  }
}
