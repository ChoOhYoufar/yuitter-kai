package services

import models.domain.User

import scala.concurrent.ExecutionContext

class SessionService (
  implicit ec: ExecutionContext
){

  def findBy(sessionKey: String): Option[User] = {
    sessionRepository.fetch(sessionKey)
  }



}
