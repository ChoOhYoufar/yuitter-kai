package syntax

import models.domain.Errors
import play.api.libs.json.{ Json, Writes }
import play.api.mvc
import play.api.mvc.Results

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ -\/, \/- }
import play.api.Logger

trait ToResultOps extends Results {

  val logger = Logger(this.getClass)

  implicit class ErrorsToOps(error: Errors) {

    def toResult: mvc.Result = error match {
      case Errors.Unexpected(_) =>
        logger.error(error.message)
        InternalServerError(Json.obj(
          "code" -> error.code,
          "message" -> error.message
        ))
      case Errors.Unauthorized => Unauthorized(Json.obj(
        "code" -> Errors.Unauthorized.code,
        "message" -> Errors.Unauthorized.message
      ))
      case Errors.RecordNotFound(_) => NotFound(Json.obj(
        "code" -> error.code,
        "message" -> error.message
      ))
      case _ => BadRequest(Json.obj(
        "code" -> error.code,
        "message" -> error.message
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
