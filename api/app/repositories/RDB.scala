package repositories

import syntax.{ DBIOResult, DBResult, Result }

trait RDB {

  // TODO yuito traitがDbioResultに依存しているのは直したい。
  def execc[A](result: DBIOResult[A]): Result[A]

  def exec[A](result: DBResult[A]): Result[A]
}
