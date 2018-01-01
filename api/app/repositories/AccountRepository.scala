package repositories

import models.domain.{ Account, AccountList, User }
import models.domain.types.{ Id, Name }
import repositories.transaction.Transaction

trait AccountRepository {

  def findById(accountId: Id[Account]): Transaction[Option[Account]]

  def create(account: Account): Transaction[Id[Account]]

  def update(account: Account): Transaction[Id[Account]]

  def searchByName(accountName: Name[String]): Transaction[AccountList]

  def listExceptForUserId(userId: Id[User]): Transaction[AccountList]

  def listByUserId(userId: Id[User]): Transaction[AccountList]

  def listFollowers(accountId: Id[User]): Transaction[AccountList]

  def listFollowees(accountId: Id[User]): Transaction[AccountList]
}
