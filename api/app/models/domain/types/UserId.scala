package models.domain.types

import models.domain.Iso

case class UserId[A](value: Long) extends AnyVal {}

object UserId {

  implicit def userIdIso[A]: Iso[Long, UserId[A]] = new Iso[Long, UserId[A]] {
    def to(value: Long): UserId[]  = UserId(value)
    def from(model: UserId[A]):
  }

}


