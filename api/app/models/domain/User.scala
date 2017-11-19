package models.domain

import java.time.LocalDateTime

import models.domain.types.{ Email, Id }

case class User(
  userId: Id[User],
  email: Email[User],
  registerDatetime: LocalDateTime,
  updateDatetime: LocalDateTime,
  versionNo: Int
)
