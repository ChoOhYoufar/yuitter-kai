package repositories

trait AbstractDBIO[A] {

  def map[B](f: A => B): AbstractDBIO[B]

  def flatMap[B](f: A => AbstractDBIO[B]): AbstractDBIO[B]
}
