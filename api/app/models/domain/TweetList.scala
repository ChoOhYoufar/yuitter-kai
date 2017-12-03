package models.domain

case class TweetList(value: Seq[Tweet]) extends AnyVal

object TweetList {

  implicit def to(a: Seq[Tweet]): TweetList = TweetList(a)
  implicit def from(b: TweetList): Seq[Tweet] = b.value
}
