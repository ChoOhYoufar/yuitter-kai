package infrastructure

import javax.inject.Inject

import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import repositories.RDB
import slick.driver.JdbcProfile
import syntax.{ DBIOResult, Result, ToEitherOps }

import scala.concurrent.ExecutionContext

class SlickDB @Inject()(
  val dbConfigProvider: DatabaseConfigProvider
)(
  implicit ex: ExecutionContext
) extends RDB with HasDatabaseConfigProvider[JdbcProfile] with ToEitherOps {

  def exec[A](result: DBIOResult[A]): Result[A] = {
    db.run(result.run).et
  }
}
