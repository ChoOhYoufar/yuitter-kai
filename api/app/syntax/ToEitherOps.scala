package syntax

import models.Errors

import scalaz.{ EitherT, \/ }

// NOTE: traitにしておけば継承してimplicitを使える
trait ToEitherOps {

  implicit class DisjunctionToEitherOps[F[_], A](fa: F[Errors \/ A]) {
    def et: EitherT[F, Errors, A] = {
      EitherT(fa)
    }
  }
}
