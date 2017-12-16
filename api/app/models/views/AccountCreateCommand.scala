package models.views

import models.domain.{ Account, User }
import models.domain.types.{ Image, Name, Status }
import models.views.types.mapper.TypeReads
import play.api.libs.json._
import utils.Constants

case class AccountCreateCommand(
  accountName: Name[Account],
  avatar: Option[Image[Account]]
) {

  def toDomain(implicit ctx: User): Account = {
    Account(
      accountId = Constants.DefaultId,
      userId = ctx.userId,
      accountName = accountName,
      accountStatus = Status.Enable,
      avatar = avatar,
      versionNo = Constants.DefaultVersionNo
    )
  }
}

object AccountCreateCommand extends TypeReads {

  implicit def accountCreateReads: Reads[AccountCreateCommand] = new Reads[AccountCreateCommand] {
    override def reads(json: JsValue): JsResult[AccountCreateCommand] = {
      for {
        accountName <- (json \ "accountName").validate[Name[Account]]
        _ <- if (accountName.isValid) JsSuccess(()) else JsError(JsPath \ "accountName", "invalid format")
        optAvatar <- (json \ "avatar").validateOpt[Image[Account]]
        _ <- optAvatar match {
          case Some(avatar) if avatar.isValid => JsSuccess(())
          case _ => JsError(JsPath \ "avatar", "invalid format")
        }
      } yield AccountCreateCommand(accountName, optAvatar)
    }
  }
}
