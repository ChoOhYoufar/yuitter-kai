package models.domain

case class TimelineList(value: List[Timeline]) extends AnyVal

object TimelineList {

  implicit def to(a: List[Timeline]): TimelineList = TimelineList(a)
  implicit def from(b: TimelineList): List[Timeline] = b.value
}
