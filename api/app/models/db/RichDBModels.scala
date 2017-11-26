package models.db

import models.domain.{ AuthUser, User }
import models.db.Tables._

/**
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
}
