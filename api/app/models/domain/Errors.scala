package models.domain

import models.domain.types.Email
import play.api.libs.json.JsError

sealed trait Errors {
  def code: String
  def message: String
}

object Errors {

//  trait Unexpected extends Errors
  case class Unexpected(msg: String) extends Errors {
    val code = "error.unexpected"
    val message: String = msg
  }
  object Unexpected {
    def apply(t: Throwable): Unexpected = new Unexpected(t.toString)
  }

  case class JsonError(error: JsError) extends Errors {
    val code = "error.json"
    val message = s"Json error occured. error=$error"
  }

  case class EmailExistsError(email: Email[_]) extends Errors {
    val code = "error.emailExists"
    val message = s"Email already exists. email=${email.value}"
  }

  case class RecordNotFound(searchElement: String) extends Errors {
    val code = "error.recordNotFound"
    val message = s"Record not found by $searchElement"
  }

  case class UpdateFailure(updateElement: Any) extends Errors {
    val code = "error.updateFailure"
    val message = s"Failed to update by${updateElement.toString}"
  }

  case object Unauthorized extends Errors {
    val code = "error.unauthorized"
    val message = s"Please login."
  }

  case object InvalidAccountIds extends Errors {
    val code = "error.invalidAccountIds"
    val message = "Some of accountIds are invalid."
  }

  case object InvalidPassword extends Errors {
    val code = "error.invalidPassword"
    val message = "Password is invalid."
  }

  case object AlreadySignIn extends Errors {
    val code = "error.alreadySignIn"
    val message = "User has session."
  }

  case class InvalidPasswordOrEmail(email: Email[_]) extends Errors {
    val code = "error.invalidPasswordOrEmail"
    val message = s"Password or email is invalid. email=${email.value}"
  }

  case object WrongVersionNo extends Errors {
    val code = "error.wrongVersionNo"
    val message = "VersionNo is wrong."
  }
}
