package models.views.commands

import models.domain._
import models.domain.types.{ Id, Text }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class TweetCommand(
  tweetText: Text[Tweet],
  accountIds: List[Id[Account]]
) {

  def toDomain: TweetCreateList = {
    TweetCreateList(
      accountIds.map { id =>
        TweetCreate(
          tweetText = tweetText,
          accountId = id
        )
      }
    )
  }
}

object TweetCommand extends TypeReads {

  implicit def tweetCreateReads: Reads[TweetCommand] = new Reads[TweetCommand] {
    override def reads(json: JsValue): JsResult[TweetCommand] = {
      for {
        tweetText <- (json \ "tweetText").validate[Text[Tweet]]
        _ <- if (tweetText.isValid) JsSuccess(()) else JsError(JsPath \ "tweetText", "invalid format")
        accountIds <- (json \ "accountIds").validate[List[Id[Account]]]
      } yield TweetCommand(tweetText, accountIds)
    }
  }
}
