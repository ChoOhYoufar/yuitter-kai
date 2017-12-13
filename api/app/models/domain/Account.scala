package models.domain

import models.domain.types._
import utils.Constants

import scalaz.\/

case class Account(
  accountId: Id[Account],
  userId: Id[User],
  accountName: Name[Account],
  accountStatus: Status[Account],
  avatar: Option[Image[Account]],
  versionNo: VersionNo[Account]
) {

  def update(accountUpdate: AccountUpdate): Errors \/ Account= {
    if (versionNo == accountUpdate.versionNo) {
      \/.right(Account(
        accountId = accountId,
        userId = userId,
        accountName = accountUpdate.accountName.getOrElse(accountName),
        accountStatus = accountUpdate.accountStatus.getOrElse(accountStatus),
        avatar = avatar.fold(avatar)(Some(_)),
        versionNo = accountUpdate.versionNo + Constants.AutoIncrementalDiff
      ))
    } else {
      \/.left(Errors.WrongVersionNo)
    }
  }
}
