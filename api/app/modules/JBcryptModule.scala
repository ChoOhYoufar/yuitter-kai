package modules

import com.google.inject.AbstractModule
import generators.Security
import infrastructure.JBcrypt
import play.api.{ Configuration, Environment }

class JBcryptModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[Security]).to(classOf[JBcrypt])
  }
}
