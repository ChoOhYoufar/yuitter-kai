package infrastructure.bcrypt.mindrot

import javax.inject.Inject

import generators.Security
import models.domain.types.{ HashedPassword, Password }
import org.mindrot.jbcrypt.BCrypt

class JBcrypt @Inject() extends Security {

  override def encrypt(source: String): String = BCrypt.hashpw(source, BCrypt.gensalt())

  override def checkPassword[T](plain: Password[T], hashed: HashedPassword[T]): Boolean = BCrypt.checkpw(plain, hashed)
}
