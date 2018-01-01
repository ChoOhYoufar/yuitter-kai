package models.views.formats

import models.domain.User
import play.api.libs.json.{ Json, Writes }

case class UserFormat(
  userId: Long,
  email: String,
  userStatus: String,
  versionNo: Int
)

object UserFormat {

  implicit val userFormatWrites: Writes[UserFormat] = Json.writes[UserFormat]

  def fromDomain(user: User): UserFormat = {
    UserFormat(
      userId = user.userId,
      email = user.email,
      userStatus = user.userStatus,
      versionNo = user.versionNo
    )
  }
}
