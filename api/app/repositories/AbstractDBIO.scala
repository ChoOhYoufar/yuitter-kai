package repositories
//
//import models.domain.Errors
//import slick.dbio.DBIO
//
//import scalaz.EitherT
//
//trait DBResult[A] {
//
//  def map[B](f: A => B): DBResult[B]
//
//  def flatMap[B](f: A => DBResult[B]): DBResult[B]
//
//}
//
//class DBIOResult[A] extends DBResult[A] {
//
//  type DBIOResult[A] = EitherT[DBIO, Errors, A]
//
//}

trait AbstractDBIO[A] {
  def map[B](f: A => B): AbstractDBIO[B]
}
