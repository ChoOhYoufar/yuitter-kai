import models.domain.Errors
import repositories.{ Transaction, RDB }

import scala.concurrent.Future
import scalaz.{ -\/, EitherT, \/, \/- }

package object syntax {

  type Result[A] = EitherT[Future, Errors, A]
  type DBResult[A] = EitherT[Transaction, Errors, A]

  object Result extends ToEitherOps {

    def apply[A](value: A): Result[A] = {
      val either: Errors \/ A = \/-(value)
      Future.successful(either).et
    }

    def error[A](error: Errors): Result[A] = {
      val either: Errors \/ A = -\/(error)
      Future.successful(either).et
    }
  }

  object DBResult extends ToEitherOps {

    def apply[A](dbio: Transaction[Errors \/ A]): DBResult[A] = {
      dbio.et
    }

    def apply[A](value: A)(implicit rdb: RDB): DBResult[A] = {
      val dbio: Transaction[Errors \/ A] = rdb.dbio(value).map(\/.right)
      DBResult(dbio)
    }

    def apply[A](either: Errors \/ A)(implicit rdb: RDB): DBResult[A] = {
      DBResult(rdb.dbio(either))
    }
  }
}
