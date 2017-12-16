package infrastructure.jdbc.slick.tables.models

import java.sql.Timestamp
import models.domain.types.{ Image, RegisterDateTime, Status, StatusCode }
import models.domain.{ Account, AuthUser, Tweet, User }
import Tables._

/**
  * DBモデル => ドメインモデルの変換処理を行う
  * implicitクラスを使ってDBモデルにtoDomainメソッドを追加する
  */
trait RichDBModels {

  implicit class RichDBUser(user: UsersRow) {

    def toDomain: User = {
      User(
        userId = user.userId,
        email = user.email,
        userStatus = Status.valueOf(user.userStatus),
        versionNo = user.versionNo
      )
    }

    def toDomainAuthUser: AuthUser = {
      AuthUser(
        user = user.toDomain,
        password = user.password
      )
    }
  }

  implicit class RichDBAccount(account: AccountsRow) {

    def toDomain: Account = {
      Account(
        accountId = account.accountId,
        userId = account.userId,
        accountName = account.accountName,
        accountStatus = Status.valueOf(account.accountStatus),
        avatar = account.avatar.map(Image(_)),
        versionNo = account.versionNo
      )
    }
  }

  implicit class RichDBTweet(tweet: TweetsRow) {

    def toDomain(account: Account): Tweet = {
      Tweet(
        tweetId = tweet.tweetId,
        tweetText = tweet.tweetText,
        tweetStatus = Status.valueOf(tweet.tweetStatus),
        registerDateTime = tweet.registerDatetime.toDomain,
        account = account
      )
    }
  }

  implicit class RichDBTimestamp(timestamp: Timestamp) {

    def toDomain[T]: RegisterDateTime[T] = {
      RegisterDateTime(
        timestamp.toLocalDateTime
      )
    }
  }
}
