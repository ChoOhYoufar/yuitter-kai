package services

import javax.inject.Inject

import models.domain.{ AccountList, Timeline, TimelineList, User }
import repositories.TweetRepository
import repositories.transaction.TransactionBuilder
import syntax.DBResult
import utils.TransactionInstances

import scala.concurrent.ExecutionContext
import scalaz.\/


class TimelineService @Inject()(
  tweetRepository: TweetRepository
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def listByAccounts(accounts: AccountList): DBResult[TimelineList] = {
    val transactions = accounts.value.map{ account =>
      tweetRepository.listByFollowees(account.accountId).map { tweetList =>
        Timeline(
          account = account,
          tweetList = tweetList
        )
      }
    }
    val dbio = builder.sequence(transactions).map(timelineList => \/.right(TimelineList(timelineList)))
    DBResult(dbio)
  }
}
