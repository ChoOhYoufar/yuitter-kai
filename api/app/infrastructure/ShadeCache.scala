package infrastructure

import play.api.libs.json.Reads
import repositories.Cache

class ShadeCache extends Cache {


  def getJson[A](key: String)(implicit reads: Reads[A]): Option[A]

}
