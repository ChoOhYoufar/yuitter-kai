package models.domain

import models.domain.types._

case class AccountUpdate(
  accountId: Id[Account],
  userId: Id[User],
  accountName: Option[Name[Account]],
  avatar: Option[Image[Account]],
  accountStatus: Option[Status[Account]],
  versionNo: VersionNo[Account]
)
