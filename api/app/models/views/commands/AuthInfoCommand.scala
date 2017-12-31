package models.views.commands

import models.domain.types.{ Email, Password }
import models.domain.{ AuthInfo, AuthUser, HashedAuthInfo }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class AuthInfoCommand(
  email: Email[AuthUser],
  password: Password[AuthUser]
) {

  def toDomainAuthInfo: AuthInfo = {
    AuthInfo(
      email = email,
      password = password
    )
  }

  def toDomainHashedAuthInfo(encrypt: String => String): HashedAuthInfo = {
    HashedAuthInfo(
      email = email,
      hashedPassword = password.hash(encrypt)
    )
  }
}

object AuthInfoCommand extends TypeReads {

  implicit def signUpReads: Reads[AuthInfoCommand] = new Reads[AuthInfoCommand] {
    def reads(json: JsValue): JsResult[AuthInfoCommand] = {
      for {
        email <- (json \ "email").validate[Email[AuthUser]]
        _ <- if (email.isValid) JsSuccess(()) else JsError(JsPath \ "email", "invalid format")
        password <- (json \ "password").validate[Password[AuthUser]]
        _ <- if (password.isValid) JsSuccess(()) else JsError(JsPath \ "password", "invalid format")
      } yield AuthInfoCommand(email, password)
    }
  }
}
