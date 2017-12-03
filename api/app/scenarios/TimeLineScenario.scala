package scenarios

import javax.inject.Inject

import models.domain.{ Account, TimeLine }
import models.domain.types.Id
import models.views.TimeLineFormat
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountService, TweetService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class TimeLineScenario @Inject()(
  accountService: AccountService,
  tweetService: TweetService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def list(accountId: Id[Account]): Result[TimeLineFormat] = {
    val result = for {
      account <- accountService.findById(accountId)
      tweetList <- tweetService.list(account)
    } yield TimeLineFormat.fromDomain(TimeLine(account, tweetList))
    runner.exec(result)
  }
}
