package models.domain

import org.joda.time.DateTime

case class User(
  userId: Long,
  email: String,
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int
) {}
