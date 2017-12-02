package scenarios

import javax.inject.Inject

import models.domain.{ Account, User }
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

  def create(account: Account)(implicit ctx: User): Result[Unit] = {
    val result = accountService.create(account).map(_ => ())
    runner.exec(result)
  }
}
