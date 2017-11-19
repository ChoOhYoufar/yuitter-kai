package models.views

import javax.inject.Inject

import models.domain.{ AuthInfo, User }
import models.domain.types.{ Email, Password }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class SignInCommand @Inject() (
  email: Email[User],
  password: Password[User]
) {

  def toDomain(encrypt: String => String): AuthInfo = {
    AuthInfo(
      email = email,
      password = password.hash(encrypt)
    )
  }
}

object SignInCommand extends TypeReads {

  implicit def signInReads: Reads[SignInCommand] = new Reads[SignInCommand] {
    def reads(json: JsValue): JsResult[SignInCommand] = {
      for {
        email <- (json \ "email").validate[Email[User]]
        _ <- if (email.isValid) JsSuccess(()) else JsError(JsPath \ "email", "invalid format")
        password <- (json \ "password").validate[Password[User]]
        _ <- if (password.isValid) JsSuccess(()) else JsError(JsPath \ "password", "invalid format")
      } yield SignInCommand(email, password)
    }
  }
}
