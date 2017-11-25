package syntax

import repositories.{ AbstractDBIO, RDB }

import scala.concurrent.ExecutionContext
import scalaz.Monad

trait AbstractDBIOInstances {

  implicit val ec: ExecutionContext
  implicit val monad: AbstractDBIOMonad = new AbstractDBIOMonad
  implicit val rdb: RDB

  class AbstractDBIOMonad extends Monad[AbstractDBIO] {
    override def point[A](value: => A): AbstractDBIO[A] = rdb.dbio(value)
    override def bind[A, B](dbio: AbstractDBIO[A])(f: A => AbstractDBIO[B]): AbstractDBIO[B] = dbio.flatMap(f)
  }
}
