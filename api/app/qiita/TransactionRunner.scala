package qiita

import repositories.transaction.Transaction

import scala.concurrent.Future

trait TransactionRunner {

  def exec[A](transaction: Transaction[A]): Future[A]
}
