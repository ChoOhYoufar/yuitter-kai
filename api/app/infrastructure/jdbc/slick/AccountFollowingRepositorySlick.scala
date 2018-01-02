package infrastructure.jdbc.slick

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Inject

import models.domain.AccountFollowing
import infrastructure.jdbc.slick.tables.models.Tables._
import slick.driver.MySQLDriver.api._
import infrastructure.jdbc.slick.transaction.SlickTransaction
import repositories.{ AccountFollowingRepository, DeleteResult }
import repositories.transaction.Transaction

import scala.concurrent.ExecutionContext

class AccountFollowingRepositorySlick @Inject()(
  implicit ec: ExecutionContext
) extends AccountFollowingRepository {

  override def create(accountFollowing: AccountFollowing): Transaction[Unit] = {
    val dbio = AccountFollowings += AccountFollowingsRow(
      followerId = accountFollowing.followerId,
      followeeId = accountFollowing.followeeId,
      registerDatetime = Timestamp.valueOf(LocalDateTime.now)
    )
    SlickTransaction(dbio.map(_ => ()))
  }

  override def delete(accountFollowing: AccountFollowing): Transaction[DeleteResult] = {
    val dbio = AccountFollowings.filter { af =>
      af.followerId === accountFollowing.followerId.value.bind &&
      af.followeeId === accountFollowing.followeeId.value.bind
    }
    .delete
    .map(DeleteResult)
    SlickTransaction(dbio)
  }
}
