import models.domain.Errors
import repositories.{ AbstractDBIO, RDB }

import scala.concurrent.Future
import scalaz.{ -\/, EitherT, \/, \/- }

package object syntax {

  type Result[A] = EitherT[Future, Errors, A]
  type DBResult[A] = EitherT[AbstractDBIO, Errors, A]

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

    def apply[A](dbio: AbstractDBIO[Errors \/ A]): DBResult[A] = {
      dbio.et
    }

    def apply[A](value: A)(implicit rdb: RDB): DBResult[A] = {
      val dbio: AbstractDBIO[Errors \/ A] = rdb.dbio(value).map(\/.right)
      DBResult(dbio)
    }

    def apply[A](either: Errors \/ A)(implicit rdb: RDB): DBResult[A] = {
      DBResult(rdb.dbio(either))
    }
  }
}
