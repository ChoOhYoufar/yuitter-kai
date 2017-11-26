package models.domain

import models.domain.types.HashedPassword

case class AuthUser(
  user: User,
  password: HashedPassword[AuthUser]
)
