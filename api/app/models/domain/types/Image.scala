package models.domain.types

import models.domain.Iso

case class Image[T](value: String) extends AnyVal {

  def isValid: Boolean = this.length <= Image.maxLength
}

object Image {

  val maxLength = 255

  implicit def to[T](a: String): Image[T] = Image(a)
  implicit def from(b: Image[_]): String = b.value
  implicit def iso[T]: Iso[String, Image[T]] = Iso(to, from)

  implicit def optTo[T](optA: Option[String]): Option[Image[T]] = optA.map(Image(_))
  implicit def optFrom(optB: Option[Image[_]]): Option[String] = optB.map(_.value)
}
