package scenarios

import javax.inject.Inject

import models.domain.{ Account, Timeline, TimelineList, User }
import models.domain.types.Id
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountService, TimelineService, TweetService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class TimelineScenario @Inject()(
  accountService: AccountService,
  tweetService: TweetService,
  timelineSercice: TimelineService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def find(accountId: Id[Account]): Result[Timeline] = {
    val result = for {
      account <- accountService.findById(accountId)
      tweetList <- tweetService.listByFollowees(account)
    } yield Timeline(account, tweetList)
    runner.exec(result)
  }

  def list()(implicit ctx: User): Result[TimelineList] = {
    val result = for {
      accounts <- accountService.listByUser(ctx.userId)
      timelineList <- timelineSercice.listByAccounts(accounts)
    } yield timelineList
    runner.exec(result)
  }
}
