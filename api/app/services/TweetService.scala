package services

import javax.inject.Inject

import models.domain.types.Id
import models.domain._
import repositories.TweetRepository
import repositories.transaction.TransactionBuilder
import syntax.DBResult
import utils.TransactionInstances

import scala.concurrent.ExecutionContext
import scalaz.\/

class TweetService @Inject()(
  tweetRepository: TweetRepository
)(
  implicit
  val ec: ExecutionContext,
  val builder: TransactionBuilder
) extends TransactionInstances {

  def listByFollowees(account: Account): DBResult[TweetList] = {
    val dbio = tweetRepository.listByFollowees(account.accountId).map(\/.right)
    DBResult(dbio)
  }

  def listByAccount(account: Account): DBResult[TweetList] = {
    val dbio = tweetRepository.listByAccount(account).map(\/.right)
    DBResult(dbio)
  }
  def create(tweetCreate: TweetCreate): DBResult[Id[Tweet]] = {
    val dbio = tweetRepository.create(tweetCreate).map(\/.right)
    DBResult(dbio)
  }

  def bulkCreate(tweetCreateList: TweetCreateList): DBResult[List[Id[Tweet]]]= {
    val acc: DBResult[List[Id[Tweet]]] = DBResult(Nil)
    tweetCreateList.value.foldLeft(acc) { (acc, tweetCreate) =>
      acc.flatMap { list =>
        create(tweetCreate).map(_ :: list)
      }
    }
  }
}
