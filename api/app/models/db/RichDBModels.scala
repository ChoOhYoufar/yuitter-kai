package models.db

import models.domain.{ Account, AuthUser, User }
import models.db.Tables._
import models.domain.types.Image

/**
  * DBモデル => ドメインモデルの変換処理を行う
  * implicitクラスを使ってDBモデルにtoDomainモデルを追加する
  */
trait RichDBModels {

  implicit class RichDBUser(user: UsersRow) {

    def toDomain: User = {
      User(
        userId = user.userId,
        email = user.email,
        registerDatetime = user.registerDatetime.toLocalDateTime,
        updateDatetime = user.updateDatetime.toLocalDateTime,
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
        avatar = account.avatar.map(Image(_)),
        registerDateTime = account.registerDatetime.toLocalDateTime,
        updateDateTime = account.updateDatetime.toLocalDateTime,
        versionNo = account.versionNo
      )
    }
  }
}
