package controllers.security

import models.domain.User
import play.api.mvc.{ Request, WrappedRequest }

case class SecureRequest[A](
  user: User,
  request: Request[A]
) extends WrappedRequest[A](request)
