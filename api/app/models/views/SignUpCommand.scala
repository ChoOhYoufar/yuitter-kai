package models.views

import models.domain.User
import models.domain.types.{ Email, Password }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class SignUpCommand(
  email: Email[User],
  password: Password[User]
)

object SignUpCommand extends TypeReads {

  implicit def signUpReads: Reads[SignUpCommand] = new Reads[SignUpCommand] {
    def reads(json: JsValue): JsResult[SignUpCommand] = {
      for {
        email <- (json \ "email").validate[Email[User]]
        _ <- if (email.isValid) JsSuccess(()) else JsError(JsPath \ "email", "invalid format")
        password <- (json \ "password").validate[Password[User]]
        _ <- if (password.isValid) JsSuccess(()) else JsError(JsPath \ "password", "invalid format")
      } yield SignUpCommand(email, password)
    }
  }
}
