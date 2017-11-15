package controllers.security

import javax.inject.Inject

import models.domain.User
import play.api.mvc.Request
import services.SessionService

class SecureAction @Inject() (
  sessionService: SessionService
) {

//  def findUserBySession(req: Request[_]): Option[User] = {
//    for {
//      key <- req.session.get("session")
//      user <- sessionService.findBy(key)
//    } yield user
//  }


}
