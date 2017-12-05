package infrastructure.jdbc.slick.dbModels

import infrastructure.jdbc.slick.dbModels.Tables._
import models.domain.types.Image
import models.domain.{ Account, AuthUser, User }

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
        versionNo = account.versionNo
      )
    }
  }
}
