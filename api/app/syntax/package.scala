import models.domain.Errors
import repositories.transaction.{ Transaction, TransactionBuilder }

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

    def apply[A](value: A)(implicit builder: TransactionBuilder): DBResult[A] = {
      val dbio: Transaction[Errors \/ A] = builder.exec(value).map(\/.right)
      DBResult(dbio)
    }

    def apply[A](either: Errors \/ A)(implicit builder: TransactionBuilder): DBResult[A] = {
      DBResult(builder.exec(either))
    }
  }
}
