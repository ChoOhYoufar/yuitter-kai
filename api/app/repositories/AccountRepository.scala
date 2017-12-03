package repositories

import models.domain.Account
import models.domain.types.Id
import repositories.transaction.Transaction

trait AccountRepository {

  def findById(accountId: Id[Account]): Transaction[Option[Account]]

  def create(account: Account): Transaction[Id[Account]]
}
