package infrastructure.jdbc.scalikejdbc.tables.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._
import java.time.{OffsetDateTime}
import scalikejdbc.jsr310._


class AccountsSpec extends Specification {

  "Accounts" should {

    val a = Accounts.syntax("a")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Accounts.find(1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Accounts.findBy(sqls.eq(a.accountId, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Accounts.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Accounts.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Accounts.findAllBy(sqls.eq(a.accountId, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Accounts.countBy(sqls.eq(a.accountId, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Accounts.create(userId = 1L, accountName = "MyString", registerDatetime = null, updateDatetime = null, versionNo = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Accounts.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Accounts.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Accounts.findAll().head
      val deleted = Accounts.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = Accounts.find(1L)
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = Accounts.findAll()
      entities.foreach(e => Accounts.destroy(e))
      val batchInserted = Accounts.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
