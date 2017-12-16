package infrastructure.jdbc.scalikejdbc.tables.models

import models.domain.types.{ Status, StatusCode }
import models.domain.{ AuthUser, User }

trait RichDBModels {

  implicit class RichDBUser(user: Users) {

    def toDomain: User = {
      User(
        userId = user.userId,
        email = user.email,
        // TODO DB更新してない
        userStatus = Status.valueOf("ENA"),
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
