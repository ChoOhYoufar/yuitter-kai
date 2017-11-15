package models.domain

trait Iso[A, B] {

  def to(a: A): B
  def from(b: B): A
}
