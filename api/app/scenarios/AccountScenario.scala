package scenarios

import javax.inject.Inject

import models.domain.types.Id
import models.domain.{ Account, AccountFollowing, AccountList, User }
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountFollowingService, AccountService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class AccountScenario @Inject()(
  accountService: AccountService,
  accountFollowingService: AccountFollowingService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def create(account: Account): Result[Unit] = {
    val result = for {
      accountId <- accountService.create(account)
      accountFollowing = AccountFollowing(accountId, accountId)
      _ <- accountFollowingService.create(accountFollowing)
    } yield ()
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

  def list()(implicit ctx: User): Result[AccountList] = {
    val result = accountService.listExceptForUserId(ctx.userId)
    runner.exec(result)
  }

  def myList()(implicit ctx: User): Result[AccountList] = {
    val result = accountService.listByUser(ctx.userId)
    runner.exec(result)
  }
}
