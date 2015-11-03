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

package domain

import hms.petstore.domain.{tagd, PetParams}
import hms.petstore.repo.PetDAO
import hms.petstore.domain.tagd
import hms.petstore.service.Main
import org.bson.types.ObjectId
import org.specs.SpecificationWithJUnit
import Main.{create,read,delete,update}

class TestOneSpecs extends SpecificationWithJUnit {


  //  def AddTestSpecs() {
  val (p1,e1) = create("Blackie", "available", 1, Array("a", "c"), Array(tagd(1, "da"), tagd(2, "ma")))
  assert(p1.isDefined)
  println("one entry inserted")
  val (p2,e2) = create("brown", "available", 1, Array("a", "d", "c"), Array(tagd(6, "da"), tagd(3, "ma")))
  assert(p2.isDefined)
  println("two entry inserted")
  val (p3,e3) = create("Kitty", "available", 2, Array("a", "j", "c"), Array(tagd(2, "ma"), tagd(4, "ma")))
  assert(p3.isDefined)
  println("three entry inserted")
  val (p4,e4) = create("Poosie", "available", 2, Array("a", "b", "m"), Array(tagd(1, "da"), tagd(5, "ma")))
  assert(p4.isDefined)
  println("four entry inserted")
  //}

  //def ReadTestSpecs{
  val a=read(p2.get)
 // assert(a.isDefined)
  println(a)
  // }

  //def UpdateTestSpecs{
  val x= PetParams(name=Some("boola"))
  println(e1)
  val c=update(x,e1)
  println(c)
  //assert(PetDAO.find(PetParams(name=Some("boola"))).nonEmpty)
  // }


  //def DelTestSpecs(){
  delete(p1.get)
  assert(PetDAO.findOneById(p1.get).isEmpty)
  println("one entry removed")
  delete(p2.get)
  assert(PetDAO.findOneById(p2.get).isEmpty)
  println("two entries removed")
  delete(p3.get)
  assert(PetDAO.findOneById(p3.get).isEmpty)
  println("three entries removed")
  delete(p4.get)
  assert(PetDAO.findOneById(p4.get).isEmpty)
  println("four entries removed")
  // }


}