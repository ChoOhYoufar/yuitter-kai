package models.views.types

import models.domain.Iso
import play.api.libs.json._

package object mapper {

  trait TypeReads {
    implicit def typeStringReads[A, B[A]](implicit iso: Iso[String, B[A]]): Reads[B[A]] = new Reads[B[A]] {
      def reads(json: JsValue): JsResult[B[A]] = {
        json.validate[String].map(s => iso.to(s))
      }
    }

    implicit def typeLongReads[A, B[A]](implicit iso: Iso[Long, B[A]]): Reads[B[A]] = new Reads[B[A]] {
      def reads(json: JsValue): JsResult[B[A]] = {
        json.validate[Long].map(s => iso.to(s))
      }
    }
  }

  trait TypeWrites {
    implicit def typeStringWrites[A, B[A]](implicit iso: Iso[String, B[A]]): Writes[B[A]] = new Writes[B[A]] {
      def writes(b: B[A]): JsValue = {
        JsString(iso.from(b))
      }
    }

    implicit def typeLongWrites[A, B[A]](implicit iso: Iso[Long, B[A]]): Writes[B[A]] = new Writes[B[A]] {
      def writes(b: B[A]): JsValue = {
        JsNumber(iso.from(b))
      }
    }
  }
}
