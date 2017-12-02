package repositories

import models.domain.Account
import models.domain.types.Id
import repositories.transaction.Transaction

trait AccountRepository {

  def create(account: Account): Transaction[Id[Account]]
}
