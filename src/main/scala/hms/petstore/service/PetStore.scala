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
import com.mongodb.casbah.{Imports, WriteConcern}
import com.novus.salat._
import hms.petstore.domain.{Category, Pet, PetParams, Tagd}
import hms.petstore.repo.{Conversions, PetDAO}


//object PetStore

object PetStore {


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
    //PetDAO.update({"id":id},{$set})
    null
  }

  def create(i: Int, n: String, s: String, c: Category, p_urls: List[String], tagz: List[Tagd]): Option[Imports.ObjectId] = {
    val p = Pet(id = i, name = n, status = s, category = c, photoUrls = p_urls, tags = tagz)
    val id = PetDAO.insert(p)
    println("Inserted id:" + id)
    id
  }

  def read(a: Int): Option[Pet] = {
    val b = PetDAO.findOne("id" $eq a)
    b
  }


  def update(a: PetParams, b: Pet): Imports.WriteResult = {
    val params_obj = Conversions.paramsToDBObject(a)
    val pet_obj = Conversions.petToDBObject(b)
    val t = PetDAO.update(params_obj, pet_obj, upsert = false, multi = false, WriteConcern.Safe)
    t
  }

  def delete(id: Int): Unit = {
    PetDAO.remove("id" $eq id)
  }


}