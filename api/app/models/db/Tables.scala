package models.db

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = AccountFollowings.schema ++ Accounts.schema ++ Tweets.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table AccountFollowings
   *  @param followerId Database column FOLLOWER_ID SqlType(BIGINT)
   *  @param followeeId Database column FOLLOWEE_ID SqlType(BIGINT)
   *  @param registerDatetime Database column REGISTER_DATETIME SqlType(DATETIME) */
  case class AccountFollowingsRow(followerId: Long, followeeId: Long, registerDatetime: java.sql.Timestamp)
  /** GetResult implicit for fetching AccountFollowingsRow objects using plain SQL queries */
  implicit def GetResultAccountFollowingsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[AccountFollowingsRow] = GR{
    prs => import prs._
    AccountFollowingsRow.tupled((<<[Long], <<[Long], <<[java.sql.Timestamp]))
  }
  /** Table description of table ACCOUNT_FOLLOWINGS. Objects of this class serve as prototypes for rows in queries. */
  class AccountFollowings(_tableTag: Tag) extends Table[AccountFollowingsRow](_tableTag, "ACCOUNT_FOLLOWINGS") {
    def * = (followerId, followeeId, registerDatetime) <> (AccountFollowingsRow.tupled, AccountFollowingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(followerId), Rep.Some(followeeId), Rep.Some(registerDatetime)).shaped.<>({r=>import r._; _1.map(_=> AccountFollowingsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column FOLLOWER_ID SqlType(BIGINT) */
    val followerId: Rep[Long] = column[Long]("FOLLOWER_ID")
    /** Database column FOLLOWEE_ID SqlType(BIGINT) */
    val followeeId: Rep[Long] = column[Long]("FOLLOWEE_ID")
    /** Database column REGISTER_DATETIME SqlType(DATETIME) */
    val registerDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("REGISTER_DATETIME")

    /** Foreign key referencing Accounts (database name FK_ACCOUNT_FOLLOWINGS_ACCOUNTS_FOLLOWEE_ID) */
    lazy val accountsFk1 = foreignKey("FK_ACCOUNT_FOLLOWINGS_ACCOUNTS_FOLLOWEE_ID", followeeId, Accounts)(r => r.accountId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Accounts (database name FK_ACCOUNT_FOLLOWINGS_ACCOUNTS_FOLLOWER_ID) */
    lazy val accountsFk2 = foreignKey("FK_ACCOUNT_FOLLOWINGS_ACCOUNTS_FOLLOWER_ID", followerId, Accounts)(r => r.accountId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (followerId,followeeId) (database name UQ_FOLLOWER_ID_AND_FOLLOWEE_ID) */
    val index1 = index("UQ_FOLLOWER_ID_AND_FOLLOWEE_ID", (followerId, followeeId), unique=true)
  }
  /** Collection-like TableQuery object for table AccountFollowings */
  lazy val AccountFollowings = new TableQuery(tag => new AccountFollowings(tag))

  /** Entity class storing rows of table Accounts
   *  @param accountId Database column ACCOUNT_ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param userId Database column USER_ID SqlType(BIGINT)
   *  @param accountName Database column ACCOUNT_NAME SqlType(VARCHAR), Length(20,true)
   *  @param avatar Database column AVATAR SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param registerDatetime Database column REGISTER_DATETIME SqlType(DATETIME)
   *  @param updateDatetime Database column UPDATE_DATETIME SqlType(DATETIME)
   *  @param versionNo Database column VERSION_NO SqlType(INT) */
  case class AccountsRow(accountId: Long, userId: Long, accountName: String, avatar: Option[String] = None, registerDatetime: java.sql.Timestamp, updateDatetime: java.sql.Timestamp, versionNo: Int)
  /** GetResult implicit for fetching AccountsRow objects using plain SQL queries */
  implicit def GetResultAccountsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Int]): GR[AccountsRow] = GR{
    prs => import prs._
    AccountsRow.tupled((<<[Long], <<[Long], <<[String], <<?[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Int]))
  }
  /** Table description of table ACCOUNTS. Objects of this class serve as prototypes for rows in queries. */
  class Accounts(_tableTag: Tag) extends Table[AccountsRow](_tableTag, "ACCOUNTS") {
    def * = (accountId, userId, accountName, avatar, registerDatetime, updateDatetime, versionNo) <> (AccountsRow.tupled, AccountsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(accountId), Rep.Some(userId), Rep.Some(accountName), avatar, Rep.Some(registerDatetime), Rep.Some(updateDatetime), Rep.Some(versionNo)).shaped.<>({r=>import r._; _1.map(_=> AccountsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ACCOUNT_ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val accountId: Rep[Long] = column[Long]("ACCOUNT_ID", O.AutoInc, O.PrimaryKey)
    /** Database column USER_ID SqlType(BIGINT) */
    val userId: Rep[Long] = column[Long]("USER_ID")
    /** Database column ACCOUNT_NAME SqlType(VARCHAR), Length(20,true) */
    val accountName: Rep[String] = column[String]("ACCOUNT_NAME", O.Length(20,varying=true))
    /** Database column AVATAR SqlType(VARCHAR), Length(255,true), Default(None) */
    val avatar: Rep[Option[String]] = column[Option[String]]("AVATAR", O.Length(255,varying=true), O.Default(None))
    /** Database column REGISTER_DATETIME SqlType(DATETIME) */
    val registerDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("REGISTER_DATETIME")
    /** Database column UPDATE_DATETIME SqlType(DATETIME) */
    val updateDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UPDATE_DATETIME")
    /** Database column VERSION_NO SqlType(INT) */
    val versionNo: Rep[Int] = column[Int]("VERSION_NO")

    /** Foreign key referencing Users (database name FK_ACCOUNTS_USERS) */
    lazy val usersFk = foreignKey("FK_ACCOUNTS_USERS", userId, Users)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Index over (accountName) (database name IX_ACCOUNTS_ACCOUNT_NAME) */
    val index1 = index("IX_ACCOUNTS_ACCOUNT_NAME", accountName)
  }
  /** Collection-like TableQuery object for table Accounts */
  lazy val Accounts = new TableQuery(tag => new Accounts(tag))

  /** Entity class storing rows of table Tweets
   *  @param tweetId Database column TWEET_ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param accountId Database column ACCOUNT_ID SqlType(BIGINT)
   *  @param tweetText Database column TWEET_TEXT SqlType(VARCHAR), Length(140,true)
   *  @param registerDatetime Database column REGISTER_DATETIME SqlType(DATETIME) */
  case class TweetsRow(tweetId: Long, accountId: Long, tweetText: String, registerDatetime: java.sql.Timestamp)
  /** GetResult implicit for fetching TweetsRow objects using plain SQL queries */
  implicit def GetResultTweetsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[TweetsRow] = GR{
    prs => import prs._
    TweetsRow.tupled((<<[Long], <<[Long], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table TWEETS. Objects of this class serve as prototypes for rows in queries. */
  class Tweets(_tableTag: Tag) extends Table[TweetsRow](_tableTag, "TWEETS") {
    def * = (tweetId, accountId, tweetText, registerDatetime) <> (TweetsRow.tupled, TweetsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(tweetId), Rep.Some(accountId), Rep.Some(tweetText), Rep.Some(registerDatetime)).shaped.<>({r=>import r._; _1.map(_=> TweetsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column TWEET_ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val tweetId: Rep[Long] = column[Long]("TWEET_ID", O.AutoInc, O.PrimaryKey)
    /** Database column ACCOUNT_ID SqlType(BIGINT) */
    val accountId: Rep[Long] = column[Long]("ACCOUNT_ID")
    /** Database column TWEET_TEXT SqlType(VARCHAR), Length(140,true) */
    val tweetText: Rep[String] = column[String]("TWEET_TEXT", O.Length(140,varying=true))
    /** Database column REGISTER_DATETIME SqlType(DATETIME) */
    val registerDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("REGISTER_DATETIME")

    /** Foreign key referencing Accounts (database name FK_TWEETS_ACCOUNTS) */
    lazy val accountsFk = foreignKey("FK_TWEETS_ACCOUNTS", accountId, Accounts)(r => r.accountId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Tweets */
  lazy val Tweets = new TableQuery(tag => new Tweets(tag))

  /** Entity class storing rows of table Users
   *  @param userId Database column USER_ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param password Database column PASSWORD SqlType(VARCHAR), Length(60,true)
   *  @param email Database column EMAIL SqlType(VARCHAR), Length(150,true)
   *  @param registerDatetime Database column REGISTER_DATETIME SqlType(DATETIME)
   *  @param updateDatetime Database column UPDATE_DATETIME SqlType(DATETIME)
   *  @param versionNo Database column VERSION_NO SqlType(INT) */
  case class UsersRow(userId: Long, password: String, email: String, registerDatetime: java.sql.Timestamp, updateDatetime: java.sql.Timestamp, versionNo: Int)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Int]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Int]))
  }
  /** Table description of table USERS. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[UsersRow](_tableTag, "USERS") {
    def * = (userId, password, email, registerDatetime, updateDatetime, versionNo) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(password), Rep.Some(email), Rep.Some(registerDatetime), Rep.Some(updateDatetime), Rep.Some(versionNo)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column USER_ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val userId: Rep[Long] = column[Long]("USER_ID", O.AutoInc, O.PrimaryKey)
    /** Database column PASSWORD SqlType(VARCHAR), Length(60,true) */
    val password: Rep[String] = column[String]("PASSWORD", O.Length(60,varying=true))
    /** Database column EMAIL SqlType(VARCHAR), Length(150,true) */
    val email: Rep[String] = column[String]("EMAIL", O.Length(150,varying=true))
    /** Database column REGISTER_DATETIME SqlType(DATETIME) */
    val registerDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("REGISTER_DATETIME")
    /** Database column UPDATE_DATETIME SqlType(DATETIME) */
    val updateDatetime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("UPDATE_DATETIME")
    /** Database column VERSION_NO SqlType(INT) */
    val versionNo: Rep[Int] = column[Int]("VERSION_NO")

    /** Uniqueness Index over (email) (database name EMAIL) */
    val index1 = index("EMAIL", email, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
