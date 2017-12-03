package repositories

import repositories.transaction.Transaction
import models.domain.{ Account, TweetList }

trait TweetRepository {

  def list(account: Account): Transaction[TweetList]
}
