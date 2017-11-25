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

  override def map[B](f: A => B): SlickDBIO[B] = SlickDBIO(value.map(f))

  override def flatMap[B](f: A => AbstractDBIO[B]): AbstractDBIO[B] = {
    val monad = new Monad[DBIO] {
      def point[A](value: => A): DBIO[A] = DBIO.successful(value)
      def bind[A, B](fa: DBIO[A])(f: A => DBIO[B]): DBIO[B] = fa.flatMap(f)
    }

    SlickDBIO(monad.bind(value)(f(_).asInstanceOf[SlickDBIO[B]].value))
  }
}
