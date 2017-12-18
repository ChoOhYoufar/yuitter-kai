package models.views.commands

import models.domain.User
import models.domain.types.{ Email, Id, Status }
import models.views.types.mapper.TypeReads
import play.api.libs.json.{ Json, Reads }

case class UserCommand(
  userId: Id[User],
  email: Email[User],
  userStatus: String,
  versionNo: Int
) {

  def toDomain: User = {
    User(
      userId = userId,
      email = email,
      userStatus = Status.valueOf(userStatus),
      versionNo = versionNo
    )
  }
}

object UserCommand extends TypeReads {

  implicit val userCommandReads: Reads[UserCommand] = Json.reads[UserCommand]
}
