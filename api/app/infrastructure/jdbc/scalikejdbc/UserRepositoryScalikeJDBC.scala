package infrastructure.jdbc.scalikejdbc

import infrastructure.jdbc.scalikejdbc.tables.models.{ RichDBModels, Users }
import models.domain.User
import models.domain.types.Id
import scalikejdbc.DB

class UserRepositoryScalikeJDBC extends RichDBModels {

  def findById(userId: Id[User]): Option[User] = {
    DB.readOnly { implicit session =>
      Users.find(userId).map(_.toDomain)
    }
  }
}
