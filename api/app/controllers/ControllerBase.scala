package controllers

import security.{ SecureAction, SecureRequest }
import models.domain.{ Errors, User }

import scalaz.{ EitherT, Monad, \/ }
import play.api.libs.json._
import play.api.mvc.{ Controller, Request }
import services.SessionService
import syntax.ToEitherOps

import scala.concurrent.ExecutionContext

trait ControllerBase extends Controller with ToEitherOps { self =>

  def sessionService: SessionService

  implicit val ec: ExecutionContext

  implicit val unitWrites: Writes[Unit] = new Writes[Unit] {
    def writes(value: Unit): JsValue = JsNull
  }

  implicit def request2SessionUser(implicit r: SecureRequest[_]): User = r.user

  implicit val SecureAction: SecureAction = new SecureAction(sessionService = self.sessionService)

  def deserialize[A, F[_]](implicit req: Request[JsValue], reads: Reads[A], monad: Monad[F]): F[Errors \/ A] = {
    val either = req.body.validate[A] match {
      case e: JsError => \/.left(Errors.JsonError(e))
      case s: JsSuccess[A] => \/.right(s.value)
    }
    monad.point(either)
  }

  def deserializeT[A, F[_]](implicit req: Request[JsValue], reads: Reads[A], monad: Monad[F]): EitherT[F, Errors, A] = {
    deserialize(req, reads, monad).et
  }
}
