package scenarios

import javax.inject.Inject

import models.domain.{ AccountFollowing, User }
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountFollowingService, AccountService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class AccountFollowingScenario @Inject()(
  accountFollowingService: AccountFollowingService,
  accountService: AccountService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def create(accountFollowing: AccountFollowing)(implicit ctx: User): Result[Unit] = {
    val result = for {
      _ <- accountService.listByUser(ctx.userId)
        .map(accounts => accounts.validateFollowerId(accountFollowing.followerId))
      _ <- accountFollowingService.create(accountFollowing)
    } yield ()
    runner.exec(result)
  }

  def delete(accountFollowing: AccountFollowing)(implicit ctx: User): Result[Unit] = {
    val result = for {
      _ <- accountService.listByUser(ctx.userId)
        .map(accounts => accounts.validateFollowerId(accountFollowing.followerId))
      _ <- accountFollowingService.delete(accountFollowing)
    } yield ()
    runner.exec(result)
  }
}
