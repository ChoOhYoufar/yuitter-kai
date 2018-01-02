package models.domain

import models.domain.types.Id

case class AccountFollowing(
  followerId: Id[Account],
  followeeId: Id[Account]
)
