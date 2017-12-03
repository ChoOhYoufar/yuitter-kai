package services

import javax.inject.Inject

import models.domain.{ Account, TweetList }
import repositories.TweetRepository
import syntax.DBResult

import scala.concurrent.ExecutionContext
import scalaz.\/

class TweetService @Inject()(
  tweetRepository: TweetRepository
)(
  implicit ex: ExecutionContext
) {

  def list(account: Account): DBResult[TweetList] = {
    val dbio = tweetRepository.list(account).map(\/.right)
    DBResult(dbio)
  }
}
