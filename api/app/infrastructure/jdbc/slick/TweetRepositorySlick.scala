package infrastructure.jdbc.slick

import javax.inject.Inject

import infrastructure.jdbc.slick.tables.models.RichDBModels
import infrastructure.jdbc.slick.tables.models.Tables._
import infrastructure.jdbc.slick.transaction.SlickTransaction
import models.domain.{ Account, TweetList }
import repositories.TweetRepository
import repositories.transaction.Transaction
import slick.driver.MySQLDriver.api._

import scala.concurrent.ExecutionContext

class TweetRepositorySlick @Inject()()(
  implicit ec: ExecutionContext
) extends TweetRepository with RichDBModels {

  override def listByFollowees(account: Account): Transaction[TweetList] = {
    val accountsWithAccountFollowings = Accounts
      .join(AccountFollowings)
      .on { case (ac, af) =>
        ac.accountId === af.followeeId
      }

    val tweets = Tweets
      .join(accountsWithAccountFollowings)
      .on { case (tw, (_, af)) =>
        tw.accountId === af.followeeId
      }
      .filter { case (_, (_, af)) =>
        af.followerId === account.accountId.value.bind
      }
      .result
      .map(_.map { case (tw, (_, _)) =>
         tw.toDomain(account)
      })
    SlickTransaction(tweets.map(TweetList(_)))
  }

  override def listByAccount(account: Account): Transaction[TweetList] = {
    val tweets = Tweets
      .filter(_.accountId === account.accountId.value.bind)
      .result
      .map(_.map(_.toDomain(account)))
    SlickTransaction(tweets.map(TweetList(_)))
  }
}
