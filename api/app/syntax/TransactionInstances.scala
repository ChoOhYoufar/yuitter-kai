package syntax

import repositories.{ Transaction, RDB }

import scala.concurrent.ExecutionContext
import scalaz.Monad

trait TransactionInstances {

  implicit val ec: ExecutionContext
  implicit val monad: TransactionMonad = new TransactionMonad
  implicit val rdb: RDB

  class TransactionMonad extends Monad[Transaction] {
    override def point[A](value: => A): Transaction[A] = rdb.transaction(value)
    override def bind[A, B](dbio: Transaction[A])(f: A => Transaction[B]): Transaction[B] = dbio.flatMap(f)
  }
}
