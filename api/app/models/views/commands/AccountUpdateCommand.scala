package models.views.commands

import models.domain.types._
import models.domain.{ Account, AccountUpdate, User }
import models.views.types.mapper.TypeReads
import play.api.libs.json._

case class AccountUpdateCommand(
  accountId: Id[Account],
  accountName: Option[Name[Account]],
  avatar: Option[Image[Account]],
  accountStatus: Option[StatusCode[Account]],
  versionNo: VersionNo[Account]
) {

  def toDomain(implicit ctx: User): AccountUpdate = {
    AccountUpdate(
      accountId = accountId,
      userId = ctx.userId,
      accountName = accountName,
      avatar = avatar,
      accountStatus = accountStatus.map(Status.valueOf(_)),
      versionNo = versionNo
    )
  }
}

object AccountUpdateCommand extends TypeReads {

  implicit def accountUpdateReads: Reads[AccountUpdateCommand] = new Reads[AccountUpdateCommand] {
    override def reads(json: JsValue): JsResult[AccountUpdateCommand] = {
      for {
        accountId <- (json \ "accountId").validate[Id[Account]]
        optAccountName <- (json \ "accountName").validateOpt[Name[Account]]
        _ <- optAccountName match {
          case Some(accountName) if accountName.isValid => JsSuccess(())
          case _ => JsError(JsPath \ "accountName", "invalid format")
        }
        optAvatar <- (json \ "avatar").validateOpt[Image[Account]]
        _ <- optAvatar match {
          case Some(avatar) if avatar.isValid => JsSuccess(())
          case _ => JsError(JsPath \ "avatar", "invalid format")
        }
        optAccountStatus <- (json \ "accountStatus").validateOpt[StatusCode[Account]]
        _ <- optAccountStatus match {
          case Some(accountStatus) if accountStatus.isValid => JsSuccess(())
          case _ => JsError(JsPath \ "accountStatus", "invalid format")
        }
        versionNo <- (json \ "versionNo").validate[VersionNo[Account]]
      } yield AccountUpdateCommand(
        accountId, optAccountName, optAvatar, optAccountStatus, versionNo
      )
    }
  }
}
