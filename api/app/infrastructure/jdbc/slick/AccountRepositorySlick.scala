package infrastructure.jdbc.slick

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import infrastructure.jdbc.slick.transaction.SlickTransaction
import models.db.Tables._
import models.db.RichDBModels
import models.domain.Account
import models.domain.types.Id
import repositories.AccountRepository
import slick.driver.MySQLDriver.api._
import repositories.transaction.Transaction

import scala.concurrent.ExecutionContext

class AccountRepositorySlick @Inject()(
  implicit ec: ExecutionContext
) extends AccountRepository with RichDBModels {

  override def create(account: Account): Transaction[Id[Account]] = {
    val dbio = Accounts returning Accounts.map(_.accountId) += AccountsRow(
      accountId = account.accountId,
      userId = account.userId,
      accountName = account.accountName,
      avatar = account.avatar,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now),
      updateDatetime = Timestamp.valueOf(LocalDateTime.now),
      versionNo = account.versionNo
    )

    SlickTransaction(dbio.map(Id(_)))
  }
}
