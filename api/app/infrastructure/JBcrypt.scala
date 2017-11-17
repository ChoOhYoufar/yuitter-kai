package infrastructure

import generators.Security
import org.mindrot.jbcrypt.BCrypt

class JBcrypt extends Security {

  override def encrypt(source: String): String = BCrypt.hashpw(source, BCrypt.gensalt())
}
