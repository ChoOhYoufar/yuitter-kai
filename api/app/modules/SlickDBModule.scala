package modules

import com.google.inject.AbstractModule
import infrastructure.{ SlickDB, UserRepositorySlick }
import play.api.{ Configuration, Environment }
import repositories.{ RDB, UserRepository }

class SlickDBModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[RDB]).to(classOf[SlickDB])
    bind(classOf[UserRepository]).to(classOf[UserRepositorySlick])
  }
}
