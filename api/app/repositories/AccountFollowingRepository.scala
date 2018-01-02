package repositories

import models.domain.AccountFollowing
import repositories.transaction.Transaction

trait AccountFollowingRepository {

  def create(accountFollowing: AccountFollowing): Transaction[Unit]

  def delete(accountFollowing: AccountFollowing): Transaction[DeleteResult]
}
