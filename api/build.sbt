name := "api"
 
version := "1.0" 
      
lazy val `api` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq( jdbc , cache , ws , specs2 % Test )

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.27",
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "org.scalaz" %% "scalaz-core" % "7.2.12",
  "io.monix" %% "shade" % "1.9.5"
)

//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

// code generation task
slick <<= slickCodeGenTask

lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = "./app"
  val username = "root"
  val password = ""
  val url = "jdbc:mysql://localhost/yuitter_kai"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.driver.MySQLDriver"
  val pkg = "models"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/models/tables/Tables.scala"
  Seq(file(fname))
}
