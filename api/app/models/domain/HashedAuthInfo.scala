package models.domain

import models.domain.types.{ Email, HashedPassword }

case class HashedAuthInfo(
  email: Email[AuthUser],
  hashedPassword: HashedPassword[AuthUser]
)
