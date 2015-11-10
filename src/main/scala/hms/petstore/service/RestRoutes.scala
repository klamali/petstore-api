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

/*
import akka.actor.ActorSystem
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import hms.petstore.domain.{Category, Pet, Tagd}
import hms.petstore.repo.PetDAO
import spray.http.{HttpHeaders, MediaTypes}
import spray.httpx.SprayJsonSupport
import spray.json._
import spray.routing.SimpleRoutingApp
import hms.petstore.service.PetStore.{addUrltoId, deleteByid, findByStatus, findByTags, findByid, postpet, updateWithid, updatepet}*/

import akka.actor.ActorSystem
import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import hms.petstore.domain.{Category, Pet, Tagd}
import spray.http.{HttpHeaders, MediaTypes}
import spray.httpx.SprayJsonSupport
import spray.json._
import spray.routing.SimpleRoutingApp

trait PetStoreProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val catFormat = jsonFormat2(Category)
  implicit val tagFormat = jsonFormat2(Tagd)
  implicit val petFormat = jsonFormat6(Pet)
}


class RestRoutesS(implicit val bindingModule: BindingModule) extends App with SimpleRoutingApp with PetStoreProtocol with Injectable {

  implicit val system = ActorSystem("my-system")

  val AccessControlAllowAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Origin", "*"
  )
  val AccessControlAllowHeadersAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Cache-Control, Pragma,X-Custom-Header"
  )
  val AccessControlAllowMethodsAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE,OPTIONS"
  )

  val ServiceRest = inject[PetStore]

  startServer(interface = "localhost", port = 8080) {
    path("pet") {
      post {
        respondWithMediaType(MediaTypes.`application/json`)
        entity(as[Pet]) {
          p =>
            complete(ServiceRest.postpet(p))
        }
      } ~
        put {
          respondWithMediaType(MediaTypes.`application/json`)
          entity(as[Pet]) {
            p =>
              complete(ServiceRest.updatepet(p))
            //complete(updatepet(p))
          }
        }
    } ~
      path("pet" / Segment) {
        e => {
          delete {
            complete {
              ServiceRest.deleteByid(e.toString.toInt)
              //deleteByid(e.toString.toInt)
            }
          } ~
            get {
              complete {
                respondWithMediaType(MediaTypes.`application/json`)
                ServiceRest.findByid(e.toString.toInt)
                //findByid(e.toString.toInt)
              }
            } ~
            post {
              entity(as[Pet]) { p =>
                complete {
                  val id = e.toString.toInt
                  ServiceRest.updateWithid(id, p)
                  //updateWithid(id, p)
                }
              }
            }
        }
      } ~
      path("pet" / Segment / "uploadImage") {
        //not working
        i =>
          post {
            parameter('image_url.as[String]) { i_url =>
              complete {
                val id = i.toString.toInt
                //addUrltoId(id, i_url)
                ServiceRest.addUrltoId(id, i_url)
              }
            }
          } ~
            path("pet" / "findByStatus") {
              respondWithMediaType(MediaTypes.`application/json`)
              parameters('status.as[String]) {
                status =>
                  complete(ServiceRest.findByStatus(status))
                //complete(findByStatus(status))
              }
            } ~
            path("pet" / "findByTags") {
              respondWithMediaType(MediaTypes.`application/json`)
              parameters('tags.as[String]) {
                tags =>
                  complete(ServiceRest.findByTags(tags))
                //complete(findByTags(tags))
              }
            }
      }
  }
  /*
        def postpet(p: Pet): Option[Pet] = {
          PetDAO.insert(p)
          val found = PetDAO.findOne("id" $eq p.id)
          found
        }

        def updatepet(p: Pet): Option[Pet] = {
          PetDAO.remove("id" $eq p.id)
          PetDAO.insert(p)
          val found = PetDAO.findOne("id" $eq p.id)
          found
        }

        def deleteByid(i: Int): String = {
          PetDAO.remove("id" $eq i)
          ""
        }

        def findByid(i: Int): Option[Pet] = {
          val s = PetDAO.findOne("id" $eq i)
          s
        }

        def updateWithid(id: Int, pet: Pet): Option[Pet] = {
          val query = "id" $eq id
          PetDAO.update(query, grater[Pet].asDBObject(pet))
          val found = PetDAO.findOne("id" $eq pet.id)
          found
        }


        def findByTags(a: String): List[Pet] = {
          val found = a.split("\\W+").flatMap(x => PetDAO.find("tags.name" $eq x))
          //found.map(grater[Pet].asObject(_)).toList
          found.toList
        }

        def findByStatus(a: String): List[Pet] = {
          val found = PetDAO.find("status" $eq a)
          //found.map(grater[Pet].asObject(_)).toList
          found.toList
        }

        def addUrltoId(id: Int, url: String): Pet = {
          val found = PetDAO.findOne("id" $eq id)
          null
        }

  */


}