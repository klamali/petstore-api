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


import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import hms.petstore.domain.Pet
import hms.petstore.repo.PetDAO


trait PetStore {
  def postpet(p: Pet): Option[Pet]

  def updatepet(p: Pet): Option[Pet]

  def deleteByid(i: Int): String

  def findByid(i: Int): Option[Pet]

  def updateWithid(id: Int, pet: Pet): Option[Pet]

  def findByTags(a: String): List[Pet]

  def findByStatus(a: String): List[Pet]

  def addUrltoId(id: Int, url: String): Option[Pet]
}


class PetStoreService extends PetStore {

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
    found.toList
  }

  def findByStatus(a: String): List[Pet] = {
    val found = PetDAO.find("status" $eq a)
    found.toList
  }

  def addUrltoId(id: Int, url: String): Option[Pet] = {
    val query = DBObject("id" -> id)
    val update = DBObject("$push" -> DBObject("photoUrls" -> url))
    val a = PetDAO.update(query, update, upsert = false, multi = false, null)
    PetDAO.findOne("id" $eq id)
  }

}