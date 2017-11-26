package infrastructure.bcrypt.mindrot

import javax.inject.Inject

import generators.Security
import org.mindrot.jbcrypt.BCrypt

class JBcrypt @Inject() extends Security {

  override def encrypt(source: String): String = BCrypt.hashpw(source, BCrypt.gensalt())

  override def checkPassword(plain: String, hashed: String): Boolean = BCrypt.checkpw(plain, hashed)
}
