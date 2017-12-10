package infrastructure.jdbc.scalikejdbc.tables.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class Accounts(
  accountId: Long,
  userId: Long,
  accountName: String,
  avatar: Option[String] = None,
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int) {

  def save()(implicit session: DBSession = Accounts.autoSession): Accounts = Accounts.save(this)(session)

  def destroy()(implicit session: DBSession = Accounts.autoSession): Int = Accounts.destroy(this)(session)

}


object Accounts extends SQLSyntaxSupport[Accounts] {

  override val schemaName = Some("PUBLIC")

  override val tableName = "ACCOUNTS"

  override val columns = Seq("ACCOUNT_ID", "USER_ID", "ACCOUNT_NAME", "AVATAR", "REGISTER_DATETIME", "UPDATE_DATETIME", "VERSION_NO")

  def apply(a: SyntaxProvider[Accounts])(rs: WrappedResultSet): Accounts = apply(a.resultName)(rs)
  def apply(a: ResultName[Accounts])(rs: WrappedResultSet): Accounts = new Accounts(
    accountId = rs.get(a.accountId),
    userId = rs.get(a.userId),
    accountName = rs.get(a.accountName),
    avatar = rs.get(a.avatar),
    registerDatetime = rs.get(a.registerDatetime),
    updateDatetime = rs.get(a.updateDatetime),
    versionNo = rs.get(a.versionNo)
  )

  val a = Accounts.syntax("a")

  override val autoSession = AutoSession

  def find(accountId: Long)(implicit session: DBSession = autoSession): Option[Accounts] = {
    withSQL {
      select.from(Accounts as a).where.eq(a.accountId, accountId)
    }.map(Accounts(a.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Accounts] = {
    withSQL(select.from(Accounts as a)).map(Accounts(a.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Accounts as a)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Accounts] = {
    withSQL {
      select.from(Accounts as a).where.append(where)
    }.map(Accounts(a.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Accounts] = {
    withSQL {
      select.from(Accounts as a).where.append(where)
    }.map(Accounts(a.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Accounts as a).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    userId: Long,
    accountName: String,
    avatar: Option[String] = None,
    registerDatetime: DateTime,
    updateDatetime: DateTime,
    versionNo: Int)(implicit session: DBSession = autoSession): Accounts = {
    val generatedKey = withSQL {
      insert.into(Accounts).namedValues(
        column.userId -> userId,
        column.accountName -> accountName,
        column.avatar -> avatar,
        column.registerDatetime -> registerDatetime,
        column.updateDatetime -> updateDatetime,
        column.versionNo -> versionNo
      )
    }.updateAndReturnGeneratedKey.apply()

    Accounts(
      accountId = generatedKey,
      userId = userId,
      accountName = accountName,
      avatar = avatar,
      registerDatetime = registerDatetime,
      updateDatetime = updateDatetime,
      versionNo = versionNo)
  }

  def batchInsert(entities: Seq[Accounts])(implicit session: DBSession = autoSession): List[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'userId -> entity.userId,
        'accountName -> entity.accountName,
        'avatar -> entity.avatar,
        'registerDatetime -> entity.registerDatetime,
        'updateDatetime -> entity.updateDatetime,
        'versionNo -> entity.versionNo))
        SQL("""insert into ACCOUNTS(
        USER_ID,
        ACCOUNT_NAME,
        AVATAR,
        REGISTER_DATETIME,
        UPDATE_DATETIME,
        VERSION_NO
      ) values (
        {userId},
        {accountName},
        {avatar},
        {registerDatetime},
        {updateDatetime},
        {versionNo}
      )""").batchByName(params: _*).apply[List]()
    }

  def save(entity: Accounts)(implicit session: DBSession = autoSession): Accounts = {
    withSQL {
      update(Accounts).set(
        column.accountId -> entity.accountId,
        column.userId -> entity.userId,
        column.accountName -> entity.accountName,
        column.avatar -> entity.avatar,
        column.registerDatetime -> entity.registerDatetime,
        column.updateDatetime -> entity.updateDatetime,
        column.versionNo -> entity.versionNo
      ).where.eq(column.accountId, entity.accountId)
    }.update.apply()
    entity
  }

  def destroy(entity: Accounts)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Accounts).where.eq(column.accountId, entity.accountId) }.update.apply()
  }

}
