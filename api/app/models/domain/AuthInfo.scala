package models.domain

import models.domain.types.{ Email, HashedPassword, Password }

import scalaz.\/
import scalaz.syntax.std.ToOptionOps

case class AuthInfo(
  email: Email[User],
  password: HashedPassword[User]
) extends ToOptionOps {
  def checkExists(searchResult: Option[User]): Errors \/ User = {
    searchResult \/> Errors.InvalidPasswordOrEmail(email)
  }
}
