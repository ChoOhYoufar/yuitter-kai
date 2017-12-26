package infrastructure.jdbc.slick

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import infrastructure.jdbc.slick.tables.models.RichDBModels
import infrastructure.jdbc.slick.tables.models.Tables._
import infrastructure.jdbc.slick.transaction.SlickTransaction
import models.domain.types.{ Id, Status }
import models.domain.{ Account, Tweet, TweetCreate, TweetList }
import repositories.TweetRepository
import repositories.transaction.Transaction
import slick.driver.MySQLDriver.api._
import utils.Constants

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

    val dbio = Tweets
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
    SlickTransaction(dbio.map(tweets => TweetList(tweets.toList)))
  }

  override def listByAccount(account: Account): Transaction[TweetList] = {
    val dbio = Tweets
      .filter(_.accountId === account.accountId.value.bind)
      .result
      .map(_.map(_.toDomain(account)))
    SlickTransaction(dbio.map(tweets => TweetList(tweets.toList)))
  }

  override def create(tweetCreate: TweetCreate): Transaction[Id[Tweet]] = {
    val dbio = Tweets returning Tweets.map(_.tweetId) += TweetsRow(
      tweetId = Constants.DefaultId,
      accountId = tweetCreate.accountId,
      tweetText = tweetCreate.tweetText,
      tweetStatus = Status.Enable.value,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now)
    )
    SlickTransaction(dbio.map(Id(_)))
  }
}
