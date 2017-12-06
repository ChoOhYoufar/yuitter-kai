package scenarios

import javax.inject.Inject

import models.domain.User
import models.domain.types.Id
import models.views.UserFormat
import services.SandBoxService

import scala.concurrent.ExecutionContext

class SandBoxScenario @Inject()(
  sandBoxService: SandBoxService
) (
  implicit
  val ec: ExecutionContext
) {

  def userFindById(userId: Id[User]): UserFormat = {
    UserFormat.fromDomain(sandBoxService.userFindById(userId))
  }

}
