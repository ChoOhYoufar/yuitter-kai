package qiita.after

import javax.inject.Inject

import qiita.{ TransactionRunner, User }
import scala.concurrent.Future

class UserService @Inject()(
  userRepository: UserRepository,
  runner: TransactionRunner
) {

  def findById(userId: Long): Future[Option[User]] = {
    runner.exec(userRepository.findById(userId))
  }
}
