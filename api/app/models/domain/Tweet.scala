package models.domain

import models.domain.types.{ Id, RegisterDateTime, Text }

case class Tweet(
  tweetId: Id[Tweet],
  tweetText: Text[Tweet],
  registerDateTime: RegisterDateTime[Tweet],
  account: Account
)
