package models.domain

import models.domain.types.{ Email, Id, VersionNo }

case class User(
  userId: Id[User],
  email: Email[User],
  versionNo: VersionNo[User]
)
