package scenarios

import javax.inject.Inject

import models.domain.types.Id
import models.domain.Account
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.AccountService
import syntax.Result
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

  def update(account: Account): Result[Unit] = {
    val result = accountService.update(account).map(_ => ())
    runner.exec(result)
  }

  def find(accountId: Id[Account]): Result[Account] = {
    val result = accountService.findById(accountId)
    runner.exec(result)
  }
}
