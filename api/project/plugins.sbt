logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.27"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.11")

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "2.5.1")
