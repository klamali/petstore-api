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
import hms.petstore.service.PetStore.{create,delete,read,update}
import hms.petstore.domain.{Pet, Category, PetParams, Tagd}
import hms.petstore.repo.PetDAO
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.BeforeAfterExample


class PetStoreSpecs extends SpecificationWithJUnit with BeforeAfterExample {

  protected def before = {
    PetDAO.remove({})
  }

  "PetStore" should {

    val entry = Pet(name = "Blackie", status = "available", category = Category(1, "ads"), photo_urls = Array("a", "c"), tags = Array(Tagd(1, "da"), Tagd(2, "ma")))
    val e1 = create("Sanky", "available", 1, Array("a", "c"), Array(Tagd(1, "da"), Tagd(2, "ma")))

    "create new entry" in {
      val e = create("Blackie", "available", 1, Array("a", "c"), Array(Tagd(1, "da"), Tagd(2, "ma")))
       PetDAO.count() mustEqual 1

    }

    "read entry" in {
      val e=read(e1.get)
      e.get mustEqual entry.copy(_id=e1.get)
    }

    val update_entry = Pet(name = "Blackie", status = "available", category = Category(1, "ads"), photo_urls = Array("a", "c"), tags = Array(Tagd(1, "da"), Tagd(2, "ma")))

    "update entry" in {
      update(PetParams(status = Some("not-available")), entry)
      PetDAO.count() mustEqual 1
      PetDAO.find(PetParams(status = Some("not-available")).mustNotEqual(0))
    }

    "delete entry" in {
      delete(e1.get)
      PetDAO.count() mustEqual 0
    }
  }

  protected def after = {
    PetDAO.remove({})
  }

}