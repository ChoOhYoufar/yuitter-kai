package repositories

import models.domain.{ Account, AccountUpdate, User }
import models.domain.types.{ Id, Name }
import repositories.transaction.Transaction

trait AccountRepository {

  def findById(accountId: Id[Account]): Transaction[Option[Account]]

  def create(account: Account): Transaction[Id[Account]]

  def update(accountUpdate: AccountUpdate): Transaction[Id[Account]]

  def searchByName(accountName: Name[String]): Transaction[Seq[Account]]

  def listByUserId(userId: Id[User]): Transaction[Seq[Account]]

  def listFollowers(accountId: Id[User]): Transaction[Seq[Account]]

  def listFollowees(accountId: Id[User]): Transaction[Seq[Account]]
}
