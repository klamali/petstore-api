/*
 *
 *  (C) Copyright 2010-2015 hSenid Mobile Solutions (Pvt) Limited.
 *   All Rights Reserved.
 *
 *   These materials are unpublished, proprietary, confidential source code of
 *   hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 *   of hSenid Mobile Solutions (Pvt) Limited.
 *
 *   hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 *   property rights in these materials.
 *
 */

package hms.petstore.service

import akka.actor.{Actor, ActorLogging}
import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import hms.petstore.domain.{Category, Pet, Tagd}
import spray.http.MediaTypes
import spray.httpx.SprayJsonSupport
import spray.json._
import spray.routing.HttpService


trait PetStoreProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val catFormat = jsonFormat2(Category)
  implicit val tagFormat = jsonFormat2(Tagd)
  implicit val petFormat = jsonFormat6(Pet)
}


class PetStoreActor(implicit val bindingModule: BindingModule) extends Actor with RestRoutes with ActorLogging {

  def actorRefFactory = context

  def receive = {
    runRoute(route)
  }
}


  trait RestRoutes extends HttpService with PetStoreProtocol with Injectable {

    lazy val serviceRest = inject[PetStore]

    val route=
      path("pet") {
        post {
          respondWithMediaType(MediaTypes.`application/json`)
          entity(as[Pet]) {
            p =>
              complete(serviceRest.postpet(p))
          }
        } ~
        put {
          respondWithMediaType(MediaTypes.`application/json`)
          entity(as[Pet]) {
            p =>
              complete(serviceRest.updatepet(p))
          }
        }
      } ~
      path("pet" / Segment) {
        e => {
          delete {
            complete {
              serviceRest.deleteByid(e.toString.toInt)
            }
          } ~
          get {
            complete {
              respondWithMediaType(MediaTypes.`application/json`)
              serviceRest.findByid(e.toString.toInt)
            }
          } ~
          post {
            entity(as[Pet]) { p =>
              complete {
                val id = e.toString.toInt
                serviceRest.updateWithid(id, p)
              }
            }
          }
        }
      } ~
      path("pet" / Segment / "uploadImage") {
        i =>
          post {
            parameter('image_url.as[String]) { i_url =>
              complete {
                val id = i.toString.toInt
                serviceRest.addUrltoId(id, i_url)
              }
            }
          }
      } ~
      path("pet" / "findByStatus") {
        respondWithMediaType(MediaTypes.`application/json`)
        parameters('status.as[String]) {
          status =>
            complete(serviceRest.findByStatus(status))
        }
      } ~
      path("pet" / "findByTags") {
        respondWithMediaType(MediaTypes.`application/json`)
        parameters('tags.as[String]) {
          tags =>
            complete(serviceRest.findByTags(tags))
        }
      }

}