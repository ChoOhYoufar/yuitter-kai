package models.db

import models.domain.User
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
  }
}
