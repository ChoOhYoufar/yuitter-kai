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

  def listByFollowees(account: Account): DBResult[TweetList] = {
    val dbio = tweetRepository.listByFollowees(account).map(\/.right)
    DBResult(dbio)
  }

  def listByAccount(account: Account): DBResult[TweetList] = {
    val dbio = tweetRepository.listByAccount(account).map(\/.right)
    DBResult(dbio)
  }
}
