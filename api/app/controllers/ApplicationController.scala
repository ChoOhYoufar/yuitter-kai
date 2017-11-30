package controllers

import javax.inject.Inject

import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext

class ApplicationController @Inject()(
  implicit val ec: ExecutionContext
) extends Controller {

  def health = Action {
    Ok
  }
}
