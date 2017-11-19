package models.domain.types

trait Hashed {
  val value: String

  def isValid: Boolean = value.length == Hashed.valueLength
}

object Hashed {

  val valueLength = 65
}
