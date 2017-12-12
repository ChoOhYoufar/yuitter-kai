package models.domain.types

sealed abstract class Status[T](
  val code: StatusCode[T]
)

object Status {

  val values = Seq(Status.Enable, Status.Disable)

  case object Enable extends Status(StatusCode("ENA"))
  case object Disable extends Status(StatusCode("DIS"))

  def find[T](code: StatusCode[T]): Status[T] = {
    code match {
      case Status.Enable.code => Status.Enable.asInstanceOf[Status[T]]
      case Status.Disable.code => Status.Disable.asInstanceOf[Status[T]]
    }
  }
}
