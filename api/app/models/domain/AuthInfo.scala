package models.domain

import models.domain.types.{ Email, HashedPassword, Password }

import scalaz.\/
import scalaz.syntax.std.ToOptionOps

case class AuthInfo(
  email: Email[User],
  password: Password[User]
)
