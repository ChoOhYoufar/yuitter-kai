package scenarios

import javax.inject.Inject

import models.domain.{ Account, TimeLine }
import models.domain.types.Id
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountService, TweetService }
import syntax.Result
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class AccountTimeLineScenario @Inject()(
  accountService: AccountService,
  tweetService: TweetService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def find(accountId: Id[Account]): Result[TimeLine] = {
    val result = for {
      account <- accountService.findById(accountId)
      tweetList <- tweetService.listByAccount(account)
    } yield TimeLine(account, tweetList)
    runner.exec(result)
  }
}
