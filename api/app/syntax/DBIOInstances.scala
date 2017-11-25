package syntax

import infrastructure.SlickDBIO
import repositories.AbstractDBIO
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext
import scalaz.Monad

trait DBIOInstances {

  implicit val ec: ExecutionContext
  implicit val monad: DBIOMonad = new DBIOMonad
  implicit val monad2: AbstractDBIOMonado = new AbstractDBIOMonado

  class DBIOMonad extends Monad[DBIO] {
    override def point[A](value: => A): DBIO[A] = DBIO.successful(value)
    override def bind[A, B](dbio: DBIO[A])(f: A => DBIO[B]) = dbio.flatMap(f)
  }

  class AbstractDBIOMonado extends Monad[AbstractDBIO] {
    override def point[A](value: => A): AbstractDBIO[A] = SlickDBIO(DBIO.successful(value))
    override def bind[A, B](dbio: AbstractDBIO[A])(f: A => AbstractDBIO[B]) = dbio.flatMap(f)
  }
}
