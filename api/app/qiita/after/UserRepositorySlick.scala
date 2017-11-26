package qiita.after

import infrastructure.jdbc.slick.transaction.SlickTransaction
import models.db.Tables.Users
import slick.driver.MySQLDriver.api._
import qiita.User

import scala.concurrent.ExecutionContext

class UserRepositorySlick(
  implicit ec: ExecutionContext
) extends UserRepository {

  def findById(userId: Long): SlickTransaction[Option[User]] = {
    val dbio = Users
      .filter(_.userId === userId.bind)
      .result
      .headOption
      .map(_.map(user => User(user.userId)))
    SlickTransaction(dbio)
  }
}
