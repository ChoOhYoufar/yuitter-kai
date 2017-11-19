package models.domain

import models.domain.types.Email
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
    val code = "error.exists"
    val message = s"Email already exists. email=${email.value}"
  }

  case object Unauthorized extends Errors {
    val code = "error.unauthorized"
    val message = s"Please login"
  }
}
