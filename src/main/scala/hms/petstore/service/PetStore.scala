package hms.petstore.service

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

import com.mongodb.casbah.Imports
import com.mongodb.casbah.Imports._
import hms.petstore.domain._
import hms.petstore.repo.{Conversions, PetDAO}


object PetStore extends App {

  val c1 = Category(1, "dog")
  val c2 = Category(2, "cat")

  def create(n: String, s: String, c: Int, p_urls: Array[String], tagz: Array[Tagd]): Option[Imports.ObjectId] = {
    val cat = c match {
      case 1 => c1
      case 2 => c2
    }
    val p = Pet(name = n, status = s, category = cat, photo_urls = p_urls, tags = tagz)
    val id = PetDAO.insert(p)
    println("Inserted id:" + id)
    id
  }

  def read(a: ObjectId): Option[Pet] = {
    val b = PetDAO.findOneById(a)
    b
  }

  def update(a: PetParams, b: Pet): Imports.WriteResult = {

    val params_obj = Conversions.paramsToDBObject(a)
    val pet_obj = Conversions.petToDBObject(b)
    val t=PetDAO.update(params_obj, pet_obj, false, false, WriteConcern.Safe)
    t
  }

  def delete(id: ObjectId) = {
    PetDAO.removeById(id)
  }


}