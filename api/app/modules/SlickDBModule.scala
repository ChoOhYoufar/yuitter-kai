package modules

import com.google.inject.AbstractModule
import infrastructure.UserRepositoryJDBC
import play.api.{ Configuration, Environment }
import repositories.UserRepository

class SlickDBModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[UserRepository]).to(classOf[UserRepositoryJDBC])
  }
}
