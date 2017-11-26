package qiita

import javax.inject.Inject

import infrastructure.jdbc.slick.transaction.SlickTransaction
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import repositories.transaction.Transaction
import slick.driver.JdbcProfile

import scala.concurrent.{ ExecutionContext, Future }

class SlickTransactionRunner @Inject()(
  val dbConfigProvider: DatabaseConfigProvider
)(
  implicit ex: ExecutionContext
) extends TransactionRunner with HasDatabaseConfigProvider[JdbcProfile] {

  override def exec[A](transaction: Transaction[A]): Future[A] = {
    val dbio = transaction.asInstanceOf[SlickTransaction[A]].value
    db.run(dbio)
  }
}
