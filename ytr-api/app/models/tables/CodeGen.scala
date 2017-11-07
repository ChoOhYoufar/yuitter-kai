package models.tables

import slick.codegen.SourceCodeGenerator

object CodeGen {

  def main(args: Array[String]): Unit = {
    SourceCodeGenerator.main(
      Array(
        "slick.driver.MySQLDriver",
        "com.mysql.jdbc.Driver",
        "jdbc:mysql://localhost/yuitter_kai",
        "app/",
        "models/tables",
        "root", ""
      )
    )
  }
}
