package infrastructure.jdbc.scalikejdbc.tables.models

import scalikejdbc._
import org.joda.time.{DateTime}

case class Users(
  userId: Long,
  password: String,
  email: String,
  registerDatetime: DateTime,
  updateDatetime: DateTime,
  versionNo: Int) {

  def save()(implicit session: DBSession = Users.autoSession): Users = Users.save(this)(session)

  def destroy()(implicit session: DBSession = Users.autoSession): Int = Users.destroy(this)(session)

}


object Users extends SQLSyntaxSupport[Users] {

  override val schemaName = Some("PUBLIC")

  override val tableName = "USERS"

  override val columns = Seq("USER_ID", "PASSWORD", "EMAIL", "REGISTER_DATETIME", "UPDATE_DATETIME", "VERSION_NO")

  def apply(u: SyntaxProvider[Users])(rs: WrappedResultSet): Users = apply(u.resultName)(rs)
  def apply(u: ResultName[Users])(rs: WrappedResultSet): Users = new Users(
    userId = rs.get(u.userId),
    password = rs.get(u.password),
    email = rs.get(u.email),
    registerDatetime = rs.get(u.registerDatetime),
    updateDatetime = rs.get(u.updateDatetime),
    versionNo = rs.get(u.versionNo)
  )

  val u = Users.syntax("u")

  override val autoSession = AutoSession

  def find(userId: Long)(implicit session: DBSession = autoSession): Option[Users] = {
    withSQL {
      select.from(Users as u).where.eq(u.userId, userId)
    }.map(Users(u.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Users] = {
    withSQL(select.from(Users as u)).map(Users(u.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Users as u)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Users] = {
    withSQL {
      select.from(Users as u).where.append(where)
    }.map(Users(u.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Users] = {
    withSQL {
      select.from(Users as u).where.append(where)
    }.map(Users(u.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Users as u).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    password: String,
    email: String,
    registerDatetime: DateTime,
    updateDatetime: DateTime,
    versionNo: Int)(implicit session: DBSession = autoSession): Users = {
    val generatedKey = withSQL {
      insert.into(Users).namedValues(
        column.password -> password,
        column.email -> email,
        column.registerDatetime -> registerDatetime,
        column.updateDatetime -> updateDatetime,
        column.versionNo -> versionNo
      )
    }.updateAndReturnGeneratedKey.apply()

    Users(
      userId = generatedKey,
      password = password,
      email = email,
      registerDatetime = registerDatetime,
      updateDatetime = updateDatetime,
      versionNo = versionNo)
  }

  def batchInsert(entities: Seq[Users])(implicit session: DBSession = autoSession): List[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'password -> entity.password,
        'email -> entity.email,
        'registerDatetime -> entity.registerDatetime,
        'updateDatetime -> entity.updateDatetime,
        'versionNo -> entity.versionNo))
        SQL("""insert into USERS(
        PASSWORD,
        EMAIL,
        REGISTER_DATETIME,
        UPDATE_DATETIME,
        VERSION_NO
      ) values (
        {password},
        {email},
        {registerDatetime},
        {updateDatetime},
        {versionNo}
      )""").batchByName(params: _*).apply[List]()
    }

  def save(entity: Users)(implicit session: DBSession = autoSession): Users = {
    withSQL {
      update(Users).set(
        column.userId -> entity.userId,
        column.password -> entity.password,
        column.email -> entity.email,
        column.registerDatetime -> entity.registerDatetime,
        column.updateDatetime -> entity.updateDatetime,
        column.versionNo -> entity.versionNo
      ).where.eq(column.userId, entity.userId)
    }.update.apply()
    entity
  }

  def destroy(entity: Users)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Users).where.eq(column.userId, entity.userId) }.update.apply()
  }

}
