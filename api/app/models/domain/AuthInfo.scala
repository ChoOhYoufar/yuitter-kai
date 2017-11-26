package models.domain

import models.domain.types.{ Email, Password }

case class AuthInfo(
  email: Email[AuthUser],
  password: Password[AuthUser]
)
