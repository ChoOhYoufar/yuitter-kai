package models.views

import models.domain.Timeline
import play.api.libs.json.{ Json, Writes }

case class TimelineFormat(
  account: AccountFormat,
  tweetList: Seq[TweetFormat]
)

object TimelineFormat {

  implicit val timelineFormatWrites: Writes[TimelineFormat] = Json.writes[TimelineFormat]

  def fromDomain(timeline: Timeline): TimelineFormat = {
    TimelineFormat(
      account = AccountFormat.fromDomain(timeline.account),
      tweetList = timeline.tweetList.map(TweetFormat.fromDomain)
    )
  }
}
