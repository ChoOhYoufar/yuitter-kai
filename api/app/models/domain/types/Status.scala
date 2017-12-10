package models.domain.types

sealed abstract class Status[T](
  val code: StatusCode[T]
)

object Status {

  val values = Seq(Status.Enable, Status.Disable)

  case object Enable extends Status(StatusCode("ENA"))
  case object Disable extends Status(StatusCode("DIS"))
}
