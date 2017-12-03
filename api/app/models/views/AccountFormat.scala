package models.views

import models.domain.Account
import play.api.libs.json.{ Json, Writes }

case class AccountFormat(
  accountId: Long,
  userId: Long,
  accountName: String,
  avatar: Option[String],
  versionNo: Int
)

object AccountFormat {

  implicit val accountFormatWrites: Writes[AccountFormat] = Json.writes[AccountFormat]

  def fromDomain(account: Account): AccountFormat = {
    AccountFormat(
      accountId = account.accountId,
      userId = account.userId,
      accountName = account.accountName,
      avatar = account.avatar,
      versionNo = account.versionNo
    )
  }
}
