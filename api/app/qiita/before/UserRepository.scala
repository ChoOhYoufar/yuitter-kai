package qiita.before

import qiita.User
import slick.dbio.DBIO

trait UserRepository {
  // せっかくtraitにしているのに返り値ですでに依存している
  def findById(userId: Long): DBIO[Option[User]]
}
