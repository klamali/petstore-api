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
import hms.petstore.domain.{petParams, tagd, category, Pet}
import hms.petstore.repo.petDAO

object Main extends App {

  val c1 = category(1, "dog")
  val c2 = category(2, "cat")

  def create(n: String, s: String, c: Int, p_urls: Array[String], tagz: Array[tagd]): (Option[Imports.ObjectId],Pet) = {
    val cat = c match {
      case 1 => c1
      case 2 => c2
    }
    val p = Pet(name = n, status = s, category = cat, photo_urls = p_urls, tags = tagz)
    val id = petDAO.insert(p)
    println("Inserted id:" + id)
    (id,p)
  }

  def read(id: ObjectId): Option[Pet] = {
   // val getResult = PetDAO.findOneById(id)
   // println(getResult)
   // getResult
    null
  }

  def update(s:petParams, p:Pet): Pet ={
    println(s)
    println(p)
   // val c=PetDAO.update(s,p)
    //c
    null
  }

  def delete(id: ObjectId) = {
    petDAO.removeById(id)
  }


}