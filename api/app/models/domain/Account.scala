package models.domain

import models.domain.types._

case class Account(
  accountId: Id[Account],
  userId: Id[User],
  accountName: Name[Account],
  avatar: Option[Image[Account]],
  versionNo: VersionNo[Account]
)
