package qiita.after

import qiita.User
import repositories.transaction.Transaction

trait UserRepository {

  def findById(userId: Long): Transaction[Option[User]]
}
