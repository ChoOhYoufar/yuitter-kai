package infrastructure

import slick.dbio.DBIO
import repositories.AbstractDBIO

case class SlickDBIO[A](
  value: DBIO[A]
) extends AbstractDBIO[A] {

  def map[B](f: A => B): SlickDBIO[B] = SlickDBIO(value.map(f))

//  def flatMap[B](f: A => SlickDBIO[B]) =
}
