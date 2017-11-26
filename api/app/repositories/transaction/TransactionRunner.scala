package repositories.transaction

import syntax.{ DBResult, Result }

trait TransactionRunner {

  def exec[A](result: DBResult[A]): Result[A]
}
