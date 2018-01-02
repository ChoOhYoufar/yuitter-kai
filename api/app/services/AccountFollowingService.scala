package services

import javax.inject.Inject

import models.domain.AccountFollowing
import repositories.AccountFollowingRepository
import syntax.DBResult

import scala.concurrent.ExecutionContext
import scalaz.\/

class AccountFollowingService @Inject()(
  accountFollowingRepository: AccountFollowingRepository
)(
  implicit ec: ExecutionContext
) {

  def create(accountFollowing: AccountFollowing): DBResult[Unit] = {
    val result = accountFollowingRepository.create(accountFollowing).map(\/.right)
    DBResult(result)
  }

  def delete(accountFollowing: AccountFollowing): DBResult[Unit] = {
    val result = accountFollowingRepository.delete(accountFollowing)
      .map(_.assertDeleted(accountFollowing))
    DBResult(result)
  }
}
