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
package repo

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import domain.{Pet, PetParams}
import scala.language.implicitConversions

object Conversions {
  implicit def paramsToDBObject(params: PetParams): DBObject =
    grater[PetParams].asDBObject(params)

  implicit def petToDBObject(c: Pet): DBObject =
    grater[Pet].asDBObject(c)
}
