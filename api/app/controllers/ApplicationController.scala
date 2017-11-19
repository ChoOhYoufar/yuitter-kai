package controllers

import javax.inject.Inject

import play.api.mvc.Action

import scala.concurrent.ExecutionContext

class ApplicationController @Inject()(
  implicit val ec: ExecutionContext
) extends ControllerBase {

  def health = Action {
    Ok
  }
}
