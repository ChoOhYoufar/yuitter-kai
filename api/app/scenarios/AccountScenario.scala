package scenarios

import javax.inject.Inject

import models.domain.{ Account, AccountUpdate }
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.AccountService
import syntax.{ DBResult, Result }
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class AccountScenario @Inject()(
  accountService: AccountService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def create(account: Account): Result[Unit] = {
    val result = accountService.create(account).map(_ => ())
    runner.exec(result)
  }

  def update(accountUpdate: AccountUpdate): Result[Unit] = {
    val result = for {
      account <- accountService.findById(accountUpdate.accountId)
      newAccount <- DBResult(account.update(accountUpdate))
      _ <- accountService.update(newAccount)
    } yield ()
    runner.exec(result)
  }
}
