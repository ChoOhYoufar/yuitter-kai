package infrastructure

import javax.inject.Inject

import models.domain.Errors
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import repositories.RDB
import slick.driver.JdbcProfile
import syntax.{ DBIOResult, DBResult, Result, ToEitherOps }

import scala.concurrent.ExecutionContext
import scalaz.\/

class SlickDB @Inject()(
  val dbConfigProvider: DatabaseConfigProvider
)(
  implicit ex: ExecutionContext
) extends RDB with HasDatabaseConfigProvider[JdbcProfile] with ToEitherOps {

  def execc[A](result: DBIOResult[A]): Result[A] = {
    db.run(result.run).et
  }

  override def exec[A](result: DBResult[A]): Result[A] = {
    val dbio = result.run

    val slick = dbio.asInstanceOf[SlickDBIO[Errors \/ A]]

    db.run(slick.value).et
  }
}
