package models.views.commands

import models.domain.types.{ Email, Password }
import models.domain.{ AuthUser, HashedAuthInfo }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class SignUpCommand(
  email: Email[AuthUser],
  password: Password[AuthUser]
) {

  def toDomain(encrypt: String => String): HashedAuthInfo = {
    HashedAuthInfo(
      email = email,
      hashedPassword = password.hash(encrypt)
    )
  }
}

object SignUpCommand extends TypeReads {

  implicit def signUpReads: Reads[SignUpCommand] = new Reads[SignUpCommand] {
    def reads(json: JsValue): JsResult[SignUpCommand] = {
      for {
        email <- (json \ "email").validate[Email[AuthUser]]
        _ <- if (email.isValid) JsSuccess(()) else JsError(JsPath \ "email", "invalid format")
        password <- (json \ "password").validate[Password[AuthUser]]
        _ <- if (password.isValid) JsSuccess(()) else JsError(JsPath \ "password", "invalid format")
      } yield SignUpCommand(email, password)
    }
  }
}
