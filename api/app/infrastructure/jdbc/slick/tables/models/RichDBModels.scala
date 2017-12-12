package infrastructure.jdbc.slick.tables.models

import models.domain.types.{ Image, Status }
import models.domain.{ Account, AuthUser, User }
import Tables._

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
        accountStatus = Status.values.filter(s => s.code.value == account.accountStatus).head.asInstanceOf[Status[Account]],
        avatar = account.avatar.map(Image(_)),
        versionNo = account.versionNo
      )
    }
  }
}
