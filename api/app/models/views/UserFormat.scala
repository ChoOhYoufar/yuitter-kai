package models.views

import java.time.LocalDateTime

import models.domain.User
import play.api.libs.json.{ Json, Writes }

case class UserFormat(
  userId: Long,
  email: String,
  registerDatetime: LocalDateTime,
  updateDatetime: LocalDateTime,
  versionNo: Int
) { }

object UserFormat {

  implicit val userViewWrites: Writes[UserFormat] = Json.writes[UserFormat]

  def fromDomain(user: User): UserFormat = {
    UserFormat(
      userId = user.userId,
      email = user.email,
      registerDatetime = user.registerDatetime,
      updateDatetime = user.updateDatetime,
      versionNo = user.versionNo
    )
  }
}
