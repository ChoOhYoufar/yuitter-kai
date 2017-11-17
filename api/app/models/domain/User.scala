package models.domain

import models.domain.types.{ Email, Id }
import org.joda.time.DateTime

case class User(
  userId: Id[User],
  email: Email[User],
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int
) {}
