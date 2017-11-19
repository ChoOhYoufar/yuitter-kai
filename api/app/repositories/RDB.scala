package repositories

import syntax.{ DBIOResult, Result }

trait RDB {

  // NOTE traitがDbioResultに依存しているのは直したい。
  def exec[A](result: DBIOResult[A]): Result[A]
}
