package models.domain

import models.domain.types.Id

import scalaz.{ -\/, \/, \/- }

case class AccountList(value: List[Account]) extends AnyVal {

  def validateAccountIds(accountIds: List[Id[Account]]): Errors \/ Unit = {
    if (accountIds.toSet.subsetOf(value.map(_.accountId).toSet)) {
      \/-(())
    } else {
      -\/(Errors.InvalidAccountIds)
    }
  }

  def validateFollowerId(followerId: Id[Account]): Errors \/ Unit = {
    if (value.map(_.accountId).contains(followerId)) {
      \/-(())
    } else {
      -\/(Errors.InvalidFollowerId)
    }
  }
}

object AccountList {

  implicit def to(a: List[Account]): AccountList = AccountList(a)
  implicit def from(b: AccountList): Seq[Account] = b.value
}
