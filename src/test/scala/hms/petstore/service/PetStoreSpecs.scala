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

import hms.petstore.domain.{Category, Pet, PetParams, Tagd}
import hms.petstore.repo.{Conversions, PetDAO}
import org.specs2.mutable.SpecificationWithJUnit



class PetStoreSpecs extends SpecificationWithJUnit with BeforeAllAfterAll {




  def beforeAll() {
    val b= Conversions.petToDBObject(null)
    PetDAO.remove(b)
  }

  def afterAll() {
    val a = Conversions.petToDBObject(null)
    PetDAO.remove(a)
  }

  "PetStore" should {
    sequential

    val entry = Pet(name = "Blackie", status = "available", category = Category(1, "ads"), photo_urls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))
    val e1 = PetStore.create("Sanky", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))

    "create new entry" in {
      val e = PetStore.create("Blackie", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))
       PetDAO.count() mustNotEqual 1
    }

    "read entry" in {
      val entry1=Pet(name = "gggd", status = "available", category = Category(1, "ads"), photo_urls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))
      val e2 = PetStore.create("gggd", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))

      val exx=PetStore.read(e2.get)
      exx.get mustEqual entry1.copy(_id=e2.get)
    }

    val update_entry = Pet(name = "Blackie", status = "available", category = Category(1, "ads"), photo_urls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))

    "update entry" in {
      PetStore.update(PetParams(status = Some("not-available")), entry)
      val a=PetParams(status = Some("not-available"))
      val params_obj = Conversions.paramsToDBObject(a)
      PetDAO.find(params_obj).mustNotEqual(0)
    }

    "delete entry" in {
      PetStore.delete(e1.get)
      PetDAO.count() mustNotEqual 2
    }
  }



}
trait BeforeAllAfterAll extends SpecificationWithJUnit  {

  protected def beforeAll()
  protected def afterAll()
}
