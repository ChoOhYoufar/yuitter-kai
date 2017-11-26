package qiita.before

import javax.inject.Inject

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import qiita.User
import slick.driver.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class UserService @Inject()(
  userRepository: UserRepository,
  // slickのconfig providerに依存している
  val dbConfigProvider: DatabaseConfigProvider
)(
  implicit ec: ExecutionContext
  // ここも依存している。罪深い。
) extends HasDatabaseConfigProvider[JdbcProfile] {

  def findById(userId: Long): Future[Option[User]] = {
    db.run(userRepository.findById(userId))
  }
}
