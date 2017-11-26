package qiita.before

import models.db.Tables.Users
import qiita.User
import slick.driver.MySQLDriver.api._
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

class UserRepositorySlick(
  implicit ec: ExecutionContext
) extends UserRepository {

  def findById(userId: Long): DBIO[Option[User]] = {
    // Usersクラスはslick-codegenで自動生成したクラスとする。
    Users
      .filter(_.userId === userId.bind)
      .result
      .headOption
      .map(_.map(user => User(user.userId)))
  }
}
