package controllers

import models.domain.Errors

import scalaz.{ EitherT, Monad, \/, \/- }
import play.api.libs.json._
import play.api.mvc.{ Controller, Request }
import syntax.ToEitherOps

import scala.concurrent.ExecutionContext

trait ControllerBase extends Controller with ToEitherOps {

  implicit val ec: ExecutionContext

  implicit val unitWrites: Writes[Unit] = new Writes[Unit] {
    def writes(value: Unit): JsValue = JsNull
  }

  def deserialize[A, F[_]](implicit req: Request[JsValue], reads: Reads[A], monad: Monad[F]): F[Errors \/ A] = {
    val either = req.body.validate[A] match {
      case e: JsError => \/.left(Errors.JsonError(e))
      case s: JsSuccess[A] => \/.right(s.value)
    }
    // NOTE: pointメソッドは引数をモナドの中身に差し込むメソッド
    monad.point(either)
  }

  def deserializeT[A, F[_]](implicit req: Request[JsValue], reads: Reads[A], monad: Monad[F]): EitherT[F, Errors, A] = {
    deserialize(req, reads, monad).et
  }
}
