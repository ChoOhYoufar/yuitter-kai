package models.views

import models.domain.User
import models.domain.types.{ Email, Id }
import models.views.types.mapper.TypeReads
import play.api.libs.json.{ Json, Reads }

case class UserCommand(
  userId: Id[User],
  email: Email[User],
  versionNo: Int
) {

  def toDomain: User = {
    User(
      userId = userId,
      email = email,
      versionNo = versionNo
    )
  }
}

object UserCommand extends TypeReads {

  implicit val userCommandReads: Reads[UserCommand] = Json.reads[UserCommand]
}
