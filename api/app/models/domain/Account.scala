package models.domain

import java.time.LocalDateTime

import models.domain.types._

case class Account(
  accountId: Id[Account],
  userId: Id[User],
  accountName: Name[Account],
  avatar: Option[Image[Account]],
  registerDateTime: LocalDateTime,
  updateDateTime: LocalDateTime,
  versionNo: Int
)
