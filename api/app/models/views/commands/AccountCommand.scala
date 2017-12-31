package models.views.commands

import models.domain.types._
import models.domain.{ Account, User }
import models.views.types.mapper.TypeReads
import play.api.libs.json._
import utils.Constants

case class AccountCommand(
  optAccountId: Option[Id[Account]],
  accountName: Name[Account],
  accountStatus: StatusCode[Account],
  avatar: Option[Image[Account]],
  optVersionNo: Option[VersionNo[Account]]
) {

  def toDomain(implicit ctx: User): Account = {
    Account(
      accountId = optAccountId.getOrElse(Constants.DefaultId),
      userId = ctx.userId,
      accountName = accountName,
      accountStatus = accountStatus.value,
      avatar = avatar,
      versionNo = optVersionNo.getOrElse(Constants.DefaultVersionNo)
    )
  }
}

object AccountCommand extends TypeReads {

  implicit def accountCreateReads: Reads[AccountCommand] = new Reads[AccountCommand] {
    override def reads(json: JsValue): JsResult[AccountCommand] = {
      for {
        optAccountId <- (json \ "optAccountId").validateOpt[Id[Account]]
        accountName <- (json \ "accountName").validate[Name[Account]]
        _ <- if (accountName.isValid) JsSuccess(()) else JsError(JsPath \ "accountName", "invalid format")
        accountStatus <- (json \ "accountStatus").validate[String]
        optAvatar <- (json \ "avatar").validateOpt[Image[Account]]
        _ <- optAvatar match {
          case Some(avatar) if avatar.isValid => JsSuccess(())
          case _ => JsError(JsPath \ "avatar", "invalid format")
        }
        optVersionNo <- (json \ "optVersionNo").validateOpt[VersionNo[Account]]
      } yield AccountCommand(optAccountId, accountName, accountStatus, optAvatar, optVersionNo)
    }
  }
}
