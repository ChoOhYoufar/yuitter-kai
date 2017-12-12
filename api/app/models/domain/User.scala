package models.domain

import models.domain.types.{ Email, Id, Status, VersionNo }

case class User(
  userId: Id[User],
  email: Email[User],
  userStatus: Status[User],
  versionNo: VersionNo[User]
)
