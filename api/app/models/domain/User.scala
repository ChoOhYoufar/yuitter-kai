package models.domain

import java.time.LocalDateTime

import models.domain.types.{ Email, HashedPassword, Id }

case class User(
  userId: Id[User],
  email: Email[User],
  optPassword: Option[HashedPassword[User]],
  registerDatetime: LocalDateTime,
  updateDatetime: LocalDateTime,
  versionNo: Int
)
