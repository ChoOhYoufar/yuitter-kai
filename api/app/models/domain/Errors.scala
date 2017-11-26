package models.domain

import models.domain.types.{ Email, Id }
import play.api.libs.json.JsError

sealed trait Errors {
  def code: String
  def message: String
}

object Errors {

  case class Unexpected(error: Throwable) extends Errors {
    val code = "error.unexpected"
    val message = s"Unexpected Error occurred. error=$error"
  }

  case class JsonError(error: JsError) extends Errors {
    val code = "error.json"
    val message = s"Json error occured. error=$error"
  }

  case class EmailExistsError(email: Email[_]) extends Errors {
    val code = "error.emailExists"
    val message = s"Email already exists. email=${email.value}"
  }

  case class EmailNotFound(email: Email[_]) extends Errors {
    val code = "error.emailNotFound"
    val message = s"Email not found. email=${email.value}"
  }

  case class IdNotFound(id: Id[_]) extends Errors {
    val code = "error.idNotFound"
    val message = s"Id not found. id=${id.value}"
  }

  case object Unauthorized extends Errors {
    val code = "error.unauthorized"
    val message = s"Please login."
  }

  case object InvalidPassword extends Errors {
    val code = "error.invalidPassword"
    val message = "Password is invalid."
  }

  // TODO 無視するエラーと通知するエラーを分けたい。
  case object AlreadySignIn extends Errors {
    val code = "error.alreadySignIn"
    val message = "User has session."
  }

  case class InvalidPasswordOrEmail(email: Email[_]) extends Errors {
    val code = "error.invalidPasswordOrEmail"
    val message = s"Password or email is invalid. email=${email.value}"
  }
}
