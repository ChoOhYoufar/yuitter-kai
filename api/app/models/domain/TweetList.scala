package models.domain

case class TweetList(value: List[Tweet]) extends AnyVal

object TweetList {

  implicit def to(a: List[Tweet]): TweetList = TweetList(a)
  implicit def from(b: TweetList): Seq[Tweet] = b.value
}
