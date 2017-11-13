package services

import models.User

import scala.concurrent.ExecutionContext

class SessionService (
  implicit ec: ExecutionContext
){

  def findBy(sessionKey: String): Option[User] = {
    sessionRepository.fetch(sessionKey)
  }



}
