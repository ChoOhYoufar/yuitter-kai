package generators

trait Security {

  def encrypt(source: String): String

  def checkPassword(plain: String, hashed: String): Boolean
}
