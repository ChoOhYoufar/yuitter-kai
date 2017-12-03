package modules

import com.google.inject.AbstractModule
import infrastructure.jdbc.slick.transaction.{ SlickTransactionBuilder, SlickTransactionRunner }
import infrastructure.jdbc.slick.{ AccountRepositorySlick, UserRepositorySlick }
import play.api.{ Configuration, Environment }
import repositories.transaction.{ TransactionBuilder, TransactionRunner }
import repositories.{ AccountRepository, UserRepository }

class SlickDBModule(environment: Environment, configuration: Configuration) extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[TransactionRunner]).to(classOf[SlickTransactionRunner])
    bind(classOf[TransactionBuilder]).to(classOf[SlickTransactionBuilder])
    bind(classOf[UserRepository]).to(classOf[UserRepositorySlick])
    bind(classOf[AccountRepository]).to(classOf[AccountRepositorySlick])
  }
}
