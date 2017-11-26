package infrastructure.jdbc.slick.transaction

import javax.inject.Inject

import repositories.transaction.TransactionBuilder
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

class SlickTransactionBuilder @Inject()(
  implicit ec: ExecutionContext
) extends TransactionBuilder {

  override def exec[A](value: A): SlickTransaction[A] = {
    SlickTransaction(DBIO.successful(value))
  }
}
