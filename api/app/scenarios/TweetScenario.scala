package scenarios

import javax.inject.Inject

import models.domain.{ TweetCreateList, User }
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import services.{ AccountService, TweetService }
import syntax.{ DBResult, Result }
import utils.TransactionInstances

import scala.concurrent.ExecutionContext

class TweetScenario @Inject()(
  tweetService: TweetService,
  accountService: AccountService,
  runner: TransactionRunner
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def create(tweetCreateList: TweetCreateList)(implicit ctx: User): Result[Unit] = {
    val result = for {
      accounts <- accountService.listByUser(ctx.userId)
      _ <- DBResult(accounts.validateIds(tweetCreateList.value.map(_.accountId)))
      _ <- tweetService.bulkCreate(tweetCreateList)
    } yield ()
    runner.exec(result)
  }
}
