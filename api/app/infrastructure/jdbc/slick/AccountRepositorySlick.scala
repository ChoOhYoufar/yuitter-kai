package infrastructure.jdbc.slick

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import infrastructure.jdbc.slick.tables.models.RichDBModels
import infrastructure.jdbc.slick.transaction.SlickTransaction
import infrastructure.jdbc.slick.tables.models.Tables._
import models.domain.{ Account, AccountList, User }
import models.domain.types.{ Id, Name, Status }
import repositories.AccountRepository
import slick.driver.MySQLDriver.api._
import repositories.transaction.Transaction
import utils.Constants

import scala.concurrent.ExecutionContext

class AccountRepositorySlick @Inject()(
  implicit ec: ExecutionContext
) extends AccountRepository with RichDBModels {

  override def findById(accountId: Id[Account]): Transaction[Option[Account]] = {
    val dbio = Accounts
      .filter(_.accountId === accountId.value.bind)
      .result
      .headOption
      .map(_.map(_.toDomain))
    SlickTransaction(dbio)
  }

  override def create(account: Account): Transaction[Id[Account]] = {
    val dbio = Accounts returning Accounts.map(_.accountId) += AccountsRow(
      accountId = account.accountId,
      userId = account.userId,
      accountName = account.accountName,
      avatar = account.avatar,
      accountStatus = Status.Enable,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now),
      updateDatetime = Timestamp.valueOf(LocalDateTime.now),
      versionNo = account.versionNo
    )
    SlickTransaction(dbio.map(Id(_)))
  }

  override def update(account: Account): Transaction[Id[Account]] = {
    val dbio = Accounts
      .filter(_.accountId === account.accountId.value.bind)
      .filter(_.userId === account.userId.value.bind)
      .filter(_.versionNo === account.versionNo.value.bind)
      .map(a => (
        a.accountName,
        a.avatar,
        a.accountStatus,
        a.versionNo,
        a.updateDatetime))
      .update(
        account.accountName,
        account.avatar,
        account.accountStatus,
        account.versionNo,
        Timestamp.valueOf(LocalDateTime.now)
      )
    SlickTransaction(dbio.map(_ => account.accountId))
  }


  override def searchByName(accountName: Name[String]): Transaction[AccountList] = {
    val dbio = Accounts
      .filter(_.accountName like s"%$accountName%" )
      .take(Constants.MaxContentsPerLoad)
      .result
      .map(accounts =>
        AccountList(
          accounts.map(_.toDomain).toList
        )
      )
    SlickTransaction(dbio)
  }

  override def listExceptForUserId(userId: Id[User]): Transaction[AccountList] = {
    val dbio = Accounts
      .filter(_.userId =!= userId.value.bind)
      .result
      .map(accounts =>
        AccountList(
          accounts.map(_.toDomain).toList
        )
      )
    SlickTransaction(dbio)
  }

  override def listByUserId(userId: Id[User]): Transaction[AccountList] = {
    val dbio = Accounts
      .filter(_.userId === userId.value.bind)
      .result
      .map(accounts =>
        AccountList(
          accounts.map(_.toDomain).toList
        )
      )
    SlickTransaction(dbio)
  }

  override def listFollowers(accountId: Id[User]): Transaction[AccountList] = {
    val dbio = Accounts
      .join(AccountFollowings)
      .on { case (ac, af) => ac.accountId === af.followerId }
      .filter { case (_, af) => af.followeeId === accountId.value.bind }
      .result
      .map(accounts =>
        AccountList(
          accounts.map { case (ac, _) => ac.toDomain }.toList
        )
      )
    SlickTransaction(dbio)
  }

  override def listFollowees(accountId: Id[User]): Transaction[AccountList] = {
    val dbio = Accounts
      .join(AccountFollowings)
      .on { case (ac, af) => ac.accountId === af.followeeId }
      .filter { case (_, af) => af.followerId === accountId.value.bind }
      .result
      .map(accounts =>
        AccountList(
          accounts.map { case (ac, _) => ac.toDomain }.toList
        )
      )
    SlickTransaction(dbio)
  }
}
