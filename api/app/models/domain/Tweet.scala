package models.domain

import models.domain.types.{ Id, RegisterDateTime, Status, Text }

case class Tweet(
  tweetId: Id[Tweet],
  tweetText: Text[Tweet],
  tweetStatus: Status,
  registerDateTime: RegisterDateTime[Tweet],
  account: Account
)
