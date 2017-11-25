package infrastructure

import slick.dbio.DBIO
import repositories.AbstractDBIO

import scala.concurrent.ExecutionContext
import scalaz.Monad

case class SlickDBIO[A](
  value: DBIO[A]
)(
  implicit ec: ExecutionContext
) extends AbstractDBIO[A] {

  val monad: Monad[DBIO] = new Monad[DBIO] {
    def point[AA](value: => AA): DBIO[AA] = DBIO.successful(value)
    def bind[AA, BB](fa: DBIO[AA])(f: AA => DBIO[BB]): DBIO[BB] = fa.flatMap(f)
  }

  override def map[B](f: A => B): SlickDBIO[B] = SlickDBIO(value.map(f))

  override def flatMap[B](f: A => AbstractDBIO[B]): AbstractDBIO[B] = {
    SlickDBIO(monad.bind(value)(f(_).asInstanceOf[SlickDBIO[B]].value))
  }
}
