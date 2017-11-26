package models.views

import javax.inject.Inject

import models.domain.{ AuthInfo, AuthUser }
import models.domain.types.{ Email, Password }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class SignInCommand @Inject() (
  email: Email[AuthUser],
  password: Password[AuthUser]
) {

  def toDomain: AuthInfo = {
    AuthInfo(
      email = email,
      password = password
    )
  }
}

object SignInCommand extends TypeReads {

  implicit def signInReads: Reads[SignInCommand] = new Reads[SignInCommand] {
    def reads(json: JsValue): JsResult[SignInCommand] = {
      for {
        email <- (json \ "email").validate[Email[AuthUser]]
        _ <- if (email.isValid) JsSuccess(()) else JsError(JsPath \ "email", "invalid format")
        password <- (json \ "password").validate[Password[AuthUser]]
        _ <- if (password.isValid) JsSuccess(()) else JsError(JsPath \ "password", "invalid format")
      } yield SignInCommand(email, password)
    }
  }
}
