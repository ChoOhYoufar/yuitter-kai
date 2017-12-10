package infrastructure.jdbc.scalikejdbc.tables.models

import models.domain.{ AuthUser, User }

trait RichDBModels {

  implicit class RichDBUser(user: Users) {

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
}
