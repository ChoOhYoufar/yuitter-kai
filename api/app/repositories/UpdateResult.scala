package repositories

import models.domain.Errors

import scalaz.{ -\/, \/, \/- }

case class UpdateResult(resultCount: Int) {

  def assertUpdated(updateElement: Any): Errors \/ Unit = {
    if (resultCount > 0) {
      \/-(())
    } else {
      -\/(Errors.UpdateFailure(updateElement))
    }
  }
}
