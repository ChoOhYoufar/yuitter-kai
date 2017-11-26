package generators

import models.domain.types.{ HashedPassword, Password }

trait Security {

  def encrypt(source: String): String

  def checkPassword[T](plain: Password[T], hashed: HashedPassword[T]): Boolean
}
