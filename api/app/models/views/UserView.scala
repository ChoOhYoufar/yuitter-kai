package models.views

import models.domain.User
import org.joda.time.DateTime
import play.api.libs.json.{ Json, Writes }

case class UserView(
  userId: Long,
  email: String,
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int
) { }

object UserView {

  implicit val userViewWrites: Writes[UserView] = Json.writes[UserView]

  def fromDomain(user: User): UserView = {
    UserView(
      userId = user.userId,
      email = user.email,
      registerDatetime = user.registerDatetime,
      updateDatetime = user.updateDatetime,
      versionNo = user.versionNo
    )
  }
}
