package repositories

import repositories.transaction.Transaction
import models.domain.{ Account, TweetList }

trait TweetRepository {

  def listByFollowees(account: Account): Transaction[TweetList]

  def listByAccount(account: Account): Transaction[TweetList]
}
