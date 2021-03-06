package services

import javax.inject.Inject

import generators.Security
import models.domain.{ Errors, User }
import models.domain.types.Id
import play.api.libs.json.JsValue
import play.api.mvc.Request
import repositories.SessionRepository

import scala.concurrent.ExecutionContext
import scalaz.\/
import scalaz.syntax.std.ToOptionOps

class SessionService @Inject()(
  sessionRepository: SessionRepository,
  security: Security
)(
  implicit ec: ExecutionContext
) extends ToOptionOps {

  def findBy(userId: Id[User]): Option[User] = {
    sessionRepository.fetch(userId)
  }

  def checkExistsSession(implicit req: Request[JsValue]): Errors \/ Unit = {
    req.session.get("session") ?
      \/.left[Errors, Unit](Errors.AlreadySignIn) |
      \/.right[Errors, Unit](())
  }

  def create(user: User): Id[User] = {
    sessionRepository.add(user)
  }

  def delete()(implicit ctx: User): Unit = {
    sessionRepository.delete(ctx)
  }
}
