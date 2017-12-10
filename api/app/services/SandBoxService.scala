package services

import javax.inject.Inject

import infrastructure.jdbc.scalikejdbc.UserRepositoryScalikeJDBC
import models.domain.User
import models.domain.types.Id

import scalaz.syntax.std.ToOptionOps

class SandBoxService @Inject()(
  userRepository: UserRepositoryScalikeJDBC
) extends ToOptionOps{

  def userFindById(userId: Id[User]): User = {
    userRepository.findById(userId).get
  }
}
