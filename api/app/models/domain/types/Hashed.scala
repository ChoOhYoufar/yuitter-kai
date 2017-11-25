package models.domain.types

trait Hashed extends Any {
  val value: String

  def isValid: Boolean = value.length == Hashed.valueLength
}

object Hashed {

  val valueLength = 60
}
