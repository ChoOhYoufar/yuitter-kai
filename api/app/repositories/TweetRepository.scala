package repositories

import models.domain.types.Id
import repositories.transaction.Transaction
import models.domain.{ Account, Tweet, TweetCreate, TweetList }

trait TweetRepository {

  def listByFollowees(accountId: Id[Account]): Transaction[TweetList]

  def listByAccount(account: Account): Transaction[TweetList]

  def create(tweetCreate: TweetCreate): Transaction[Id[Tweet]]
}
