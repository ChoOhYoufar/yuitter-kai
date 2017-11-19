package syntax

import models.domain.Errors
import play.api.http.Status
import play.api.libs.json.{ Json, Writes }
import play.api.mvc
import play.api.mvc.Results

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ -\/, \/- }

trait ResultOps extends Results with Status {

  implicit class ErrorsToOps(errors: Errors) {

    // TODO: yuitoここにlogger仕込みたい。
    def toResult: mvc.Result = errors match {
      case Errors.Unauthorized => Unauthorized(Json.obj(
        "code" -> Errors.Unauthorized.code,
        "message" -> Errors.Unauthorized.message
      ))
      case _ => BadRequest(Json.obj(
        "code" -> errors.code,
        "message" -> errors.message
      ))
    }
  }

  implicit class ResultToResultOps[A](result: syntax.Result[A]) {

    def toResult(implicit ec: ExecutionContext, writes: Writes[A]): Future[mvc.Result] = {
      toResult { value =>
        Ok(Json.toJson(value))
      }
    }

    def toResult(f: A => mvc.Result)(implicit ec: ExecutionContext): Future[mvc.Result] = {
      result.run
        .recover {
          case t =>
            -\/(Errors.Unexpected(t))
        }
        .map {
          case \/-(a) => f(a)
          case -\/(e) => e.toResult
        }
    }
  }
}
