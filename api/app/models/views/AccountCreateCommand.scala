package models.views

import models.domain.Account
import models.domain.types.{ Image, Name }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class AccountCreateCommand(
  accountName: Name[Account],
  avatar: Image[Account]
)

object AccountCreateCommand extends TypeReads {

  implicit def accountCreateReads: Reads[AccountCreateCommand] = new Reads[AccountCreateCommand] {
    override def reads(json: JsValue): JsResult[AccountCreateCommand] = {
      for {
        accountName <- (json \ "accountName").validate[Name[Account]]
        _ <- if (accountName.isValid) JsSuccess(()) else JsError(JsPath \ "accountName", "invalid format")
        avatar <- (json \ "avatar").validate[Image[Account]]
        _ <- if (avatar.isValid) JsSuccess(()) else JsError(JsPath \ "avatar", "invalid format")
      } yield AccountCreateCommand(accountName, avatar)
    }
  }
}
