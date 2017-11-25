import infrastructure.SlickDBIO
import models.domain.Errors
import repositories.AbstractDBIO
import slick.dbio.DBIO

import scala.concurrent.{ ExecutionContext, Future }
import scalaz.{ -\/, EitherT, \/, \/- }

package object syntax {

  type Result[A] = EitherT[Future, Errors, A]
  type DBIOResult[A] = EitherT[DBIO, Errors, A]
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

  object DBIOResult extends ToEitherOps {

    def apply[A](either: DBIO[Errors \/ A]): DBIOResult[A] = {
      either.et
    }

    def apply[A](value: A): DBIOResult[A] = {
      val dbio: DBIO[Errors \/ A] = DBIO.successful(\/-(value))
      dbio.et
    }

    def error[A](error: Errors): DBIOResult[A] = {
      val dbio: DBIO[Errors \/ A] = DBIO.successful(-\/(error))
      dbio.et
    }
  }

  object DBResult extends ToEitherOps {

    def apply[A](either: AbstractDBIO[Errors \/ A]): DBResult[A] = {
      either.et
    }

    def apply[A](value: A)(implicit ec: ExecutionContext): DBResult[A] = {
      val dbio: AbstractDBIO[Errors \/ A] = SlickDBIO(DBIO.successful(\/.right[Errors, A](value)))
      dbio.et
    }
  }
}
