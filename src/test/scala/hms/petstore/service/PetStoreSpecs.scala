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

  /*
  "PetStore" should {
    sequential

    val entry = Pet(id=1,name = "Blackie", status = "available", category = Category(1, "ads"), photoUrls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))
    val e1 = PetStore.create(2,"Sanky", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))

    "create new entry" in {
      val e = PetStore.create(3,"Blackie", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))
       PetDAO.count() mustNotEqual 1
    }

    "read entry" in {
      val entry1=Pet(id=4,name = "gggd", status = "available", category = Category(1, "ads"), photoUrls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))
      val e2 = PetStore.create(4,"gggd", "available", Category(1, "ads"), List("a", "c"), List(Tagd(1, "da"), Tagd(2, "ma")))

      val exx=PetStore.read(entry1.id)
      exx.get mustEqual entry1.copy(id=entry1.id)
    }

    val update_entry = Pet(id=3,name = "Blackie", status = "available", category = Category(1, "ads"), photoUrls = List("a", "c"), tags = List(Tagd(1, "da"), Tagd(2, "ma")))

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
*/


}
trait BeforeAllAfterAll extends SpecificationWithJUnit  {

  protected def beforeAll()
  protected def afterAll()
}
