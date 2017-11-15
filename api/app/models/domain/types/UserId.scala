package models.domain.types

import models.domain.Iso

case class UserId(value: Long) extends AnyVal {}

object UserId {

  implicit def userIdIso: Iso[Long, UserId] = new Iso[Long, UserId] {
    def to(value: Long): UserId  = UserId(value)
    def from(model: UserId): Long = model.value
  }
}
