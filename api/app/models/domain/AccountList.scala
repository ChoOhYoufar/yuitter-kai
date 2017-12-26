package models.domain

import models.domain.types.Id

import scalaz.{ -\/, \/, \/- }

case class AccountList(value: List[Account]) extends AnyVal {

  def validateIds(accountIds: List[Id[Account]]): Errors \/ Unit = {
    if (accountIds.toSet.subsetOf(value.map(_.accountId).toSet)) {
      \/-(())
    } else {
      -\/(Errors.InvalidAccountIds)
    }
  }
}

object AccountList {

  implicit def to(a: List[Account]): AccountList = AccountList(a)
  implicit def from(b: AccountList): Seq[Account] = b.value
}
