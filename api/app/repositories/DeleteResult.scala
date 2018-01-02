package repositories

import models.domain.Errors

import scalaz.{ -\/, \/, \/- }

case class DeleteResult(resultCount: Int) {

  def assertDeleted(deleteElement: Any): Errors \/ Unit = {
    if (resultCount > 0) {
      \/-(())
    } else {
      -\/(Errors.DeleteFailure(deleteElement))
    }
  }
}
