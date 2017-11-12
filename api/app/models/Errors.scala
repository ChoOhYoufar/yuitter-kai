package models

import play.api.libs.json.JsError

sealed trait Errors {
  def code: String
  def message: String
}

object Errors {

  case object Unexpected extends Errors {
    val code = "error.unexpected"
    val message = "Unexpected Error occurred."
  }

  case class JsonError(error: JsError) extends Errors {
    val code = "error.json"
    val message = s"Json error occured. error=$error"
  }
}
