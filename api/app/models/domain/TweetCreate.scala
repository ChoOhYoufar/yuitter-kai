package models.domain

import models.domain.types.{ Id, Text }

case class TweetCreate(
  tweetText: Text[Tweet],
  accountId: Id[Account]
)
