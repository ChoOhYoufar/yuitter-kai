package models.domain

import models.domain.types.{ Email, Password }

case class AuthInfo(
  email: Email[User],
  password: Password[User]
)
