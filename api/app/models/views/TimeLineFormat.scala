package models.views

import models.domain.TimeLine
import play.api.libs.json.{ Json, Writes }

case class TimeLineFormat(
  account: AccountFormat,
  tweetList: Seq[TweetFormat]
)

object TimeLineFormat {

  implicit val timeLineFormatWrites: Writes[TimeLineFormat] = Json.writes[TimeLineFormat]

  def fromDomain(timeLine: TimeLine): TimeLineFormat = {
    TimeLineFormat(
      account = AccountFormat.fromDomain(timeLine.account),
      tweetList = timeLine.tweetList.map(TweetFormat.fromDomain)
    )
  }
}
