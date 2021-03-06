package services

import javax.inject.Inject

import models.domain.{ Account, AccountList, User }
import models.domain.types.Id
import repositories.AccountRepository
import syntax.DBResult

import scala.concurrent.ExecutionContext
import scalaz.\/

class AccountService @Inject()(
  accountRepository: AccountRepository
)(
  implicit ec: ExecutionContext
) {

  def findById(accountId: Id[Account]): DBResult[Account] = {
    val dbio = accountRepository
      .findById(accountId)
      .map(accountId.assertExist)
    DBResult(dbio)
  }

  def create(account: Account): DBResult[Id[Account]] = {
    val dbio = accountRepository.create(account).map(\/.right)
    DBResult(dbio)
  }

  def update(account: Account): DBResult[Id[Account]] = {
    val dbio = accountRepository.update(account).map(\/.right)
    DBResult(dbio)
  }

  def listExceptForUserId(userId: Id[User]): DBResult[AccountList] = {
    val dbio = accountRepository.listExceptForUserId(userId).map(\/.right)
    DBResult(dbio)
  }

  def listByUser(userId: Id[User]): DBResult[AccountList] = {
    val dbio = accountRepository.listByUserId(userId).map(\/.right)
    DBResult(dbio)
  }
}
