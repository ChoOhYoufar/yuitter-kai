package infrastructure

import slick.dbio.DBIO
import repositories.Transaction

import scala.concurrent.ExecutionContext
import scalaz.Monad

case class SlickTransaction[A](
  value: DBIO[A]
)(
  implicit ec: ExecutionContext
) extends Transaction[A] {

  val monad: Monad[DBIO] = new Monad[DBIO] {
    def point[AA](value: => AA): DBIO[AA] = DBIO.successful(value)
    def bind[AA, BB](fa: DBIO[AA])(f: AA => DBIO[BB]): DBIO[BB] = fa.flatMap(f)
  }

  override def map[B](f: A => B): SlickTransaction[B] = SlickTransaction(value.map(f))

  override def flatMap[B](f: A => Transaction[B]): Transaction[B] = {
    SlickTransaction(monad.bind(value)(f(_).asInstanceOf[SlickTransaction[B]].value))
  }
}
