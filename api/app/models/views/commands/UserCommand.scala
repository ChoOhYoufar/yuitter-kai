package models.views.commands

import models.domain.User
import models.domain.types._
import models.views.types.mapper.TypeReads
import play.api.libs.json.{ Json, Reads }

case class UserCommand(
  userId: Id[User],
  email: Email[User],
  userStatus: StatusCode[User],
  versionNo: VersionNo[User]
) {

  def toDomain: User = {
    User(
      userId = userId,
      email = email,
      userStatus = userStatus.value,
      versionNo = versionNo
    )
  }
}

object UserCommand extends TypeReads {

  implicit val userCommandReads: Reads[UserCommand] = Json.reads[UserCommand]
}
