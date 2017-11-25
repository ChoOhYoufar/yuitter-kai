package repositories

import syntax.{ DBResult, Result }

trait RDB {

  def exec[A](result: DBResult[A]): Result[A]

  def dbio[A](value: A): AbstractDBIO[A]
}
