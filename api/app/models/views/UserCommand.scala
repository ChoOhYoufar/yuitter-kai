package models.views

import models.domain.User
import org.joda.time.DateTime
import play.api.libs.json.{ Json, Reads }
import models.domain.types.Id._

case class UserCommand(
  userId: Long,
  email: String,
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int
) {

  def toDomain: User = {
    User(
      userId = userId,
      email = email,
      registerDatetime = registerDatetime,
      updateDatetime = updateDatetime,
      versionNo = versionNo
    )
  }
}

object UserCommand {

  implicit val userCommandReads: Reads[UserCommand] = Json.reads[UserCommand]
}
