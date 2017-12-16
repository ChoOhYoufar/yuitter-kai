package models.domain.types

import models.domain.{ EnumCompanion, Enum }

sealed abstract class Status(val value: String) extends Enum[String]

object Status extends EnumCompanion[String, Status] {

  val values = Seq(Status.Enable, Status.Disable)

  case object Enable extends Status("ENA")
  case object Disable extends Status("DIS")
}
