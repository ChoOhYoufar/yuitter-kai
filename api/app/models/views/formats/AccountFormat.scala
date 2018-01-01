package models.views.formats

import models.domain.Account
import play.api.libs.json.{ Json, Writes }

case class AccountFormat(
  accountId: Long,
  accountName: String,
  accountStatus: String,
  avatar: Option[String],
  versionNo: Int
)

object AccountFormat {

  implicit val accountFormatWrites: Writes[AccountFormat] = Json.writes[AccountFormat]

  def fromDomain(account: Account): AccountFormat = {
    AccountFormat(
      accountId = account.accountId,
      accountName = account.accountName,
      accountStatus = account.accountStatus,
      avatar = account.avatar,
      versionNo = account.versionNo
    )
  }
}
