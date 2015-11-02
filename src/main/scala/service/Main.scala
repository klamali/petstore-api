package service

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


import java.util.Locale.Category

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import domain.Pet
import domain.category
import domain.tagd
import repo.PetDAO

object Main extends App {

val c1=category(1,"dog")
val c2=category(2,"cat")

  val t1=tagd(1,"a")
  //val t2=tag(2,"b")
  //val t=Array(t1,t2)



  def create(n:String, s:String, c:Int, p_urls:Array[String], tagz:Array[tagd]) {

    val cat=c match {
      case 1 =>c1
      case 2 =>c2
    }

    val p = Pet(name = n, status = s, category = cat, photo_urls = p_urls, tags = tagz)

    val id = PetDAO.insert(p)
    println("Inserted id:" + id)
  }


  val found = PetDAO.findOne(MongoDBObject("name" -> "Foo", "status"->"available", "category"->"dog", "photo_urls"->Array("Zara", "Nuha", "Ayan")))
      println("Found record for name ->Foo:" )

//      val dbo = grater[Pet].asDBObject(p)
  //    println("Converted DBObject:" + dbo)




//  def find(n:String)

  }
