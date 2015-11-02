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

import org.specs.SpecificationWithJUnit
import service.Main.create


class TestOneSpecs extends SpecificationWithJUnit{


//  def addtestSpecs() {

    create("Blackie", "available", 1, Array("a", "c"), Array(tagd(1, "da"), tagd(2, "ma")))
    println("one entry inserted")
    create("brown", "available", 1, Array("a", "d", "c"), Array(tagd(6, "da"), tagd(3, "ma")))
    println("two entry inserted")
    create("Kitty", "available", 2, Array("a", "j", "c"), Array(tagd(2, "ma"), tagd(4, "ma")))
    println("three entry inserted")
    create("Poosie", "available", 2, Array("a", "b", "m"), Array(tagd(1, "da"), tagd(5, "ma")))
    println("four entry inserted")
  //}




}
