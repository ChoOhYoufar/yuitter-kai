package infrastructure.jdbc.slick.tables.models

import models.domain.types.{ Image, Status, StatusCode }
import models.domain.{ Account, AuthUser, User }
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
        userStatus = Status.find(user.userStatus.asInstanceOf[StatusCode[User]]),
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
        accountStatus = Status.find(account.accountStatus.asInstanceOf[StatusCode[Account]]),
        avatar = account.avatar.map(Image(_)),
        versionNo = account.versionNo
      )
    }
  }
}
