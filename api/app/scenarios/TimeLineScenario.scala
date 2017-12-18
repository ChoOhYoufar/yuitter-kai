package scenarios

import javax.inject.Inject

import models.domain.{ Account, Timeline }
import models.domain.types.Id
import models.views.formats.TimelineFormat
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountService, TweetService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class TimelineScenario @Inject()(
  accountService: AccountService,
  tweetService: TweetService,
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
}
