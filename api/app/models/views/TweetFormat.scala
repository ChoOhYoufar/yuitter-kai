package models.views

import java.time.LocalDateTime

import models.domain.Tweet
import play.api.libs.json.{ Json, Writes }

case class TweetFormat(
  tweetId: Long,
  tweetText: String,
  registerDateTime: LocalDateTime,
  account: AccountFormat
)

object TweetFormat {

  implicit val tweetFormatWrites: Writes[TweetFormat] = Json.writes[TweetFormat]

  def fromDomain(tweet: Tweet): TweetFormat = {
    TweetFormat(
      tweetId = tweet.tweetId,
      tweetText = tweet.tweetText,
      registerDateTime = tweet.registerDateTime,
      account = AccountFormat.fromDomain(tweet.account)
    )
  }
}
