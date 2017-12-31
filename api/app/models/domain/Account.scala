package models.domain

import models.domain.types._
import utils.Constants

import scalaz.\/

case class Account(
  accountId: Id[Account],
  userId: Id[User],
  accountName: Name[Account],
  accountStatus: Status,
  avatar: Option[Image[Account]],
  versionNo: VersionNo[Account]
)
