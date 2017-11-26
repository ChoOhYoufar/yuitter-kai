package infrastructure

import javax.inject.Inject

import models.domain.Errors
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import repositories.RDB
import slick.driver.JdbcProfile
import slick.dbio.DBIO
import syntax.{ DBResult, Result, ToEitherOps }

import scala.concurrent.ExecutionContext
import scalaz.\/

class SlickDB @Inject()(
  val dbConfigProvider: DatabaseConfigProvider
)(
  implicit ex: ExecutionContext
) extends RDB with HasDatabaseConfigProvider[JdbcProfile] with ToEitherOps {

  override def exec[A](result: DBResult[A]): Result[A] = {
    val transaction = result.run

    val slick = transaction.asInstanceOf[SlickTransaction[Errors \/ A]]

    db.run(slick.value).et
  }

  override def transaction[A](value: A): SlickTransaction[A] = {
    SlickTransaction(DBIO.successful(value))
  }
}
