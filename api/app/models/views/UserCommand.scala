package models.views

import models.domain.User
import models.domain.types.{ Email, Id }
import models.views.types.mapper.TypeReads
import org.joda.time.DateTime
import play.api.libs.json.{ Json, Reads }

case class UserCommand(
  userId: Id[User],
  email: Email[User],
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

object UserCommand extends TypeReads {

  implicit val userCommandReads: Reads[UserCommand] = Json.reads[UserCommand]
}
