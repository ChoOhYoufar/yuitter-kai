package services

import javax.inject.Inject

import models.domain.User
import repositories.SessionRepository

import scala.concurrent.ExecutionContext

class SessionService @Inject() (
  sessionRepository: SessionRepository
)(
  implicit ec: ExecutionContext
){

  def findBy(sessionKey: String): Option[User] = {
    sessionRepository.fetch(sessionKey).map(_.toDomain)
  }
}
