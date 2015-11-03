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
package hms.petstore.repo

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
import hms.petstore.domain.{petParams,pet}
import scala.language.implicitConversions

object conversions {
  implicit def paramsToDBObject(params: petParams): DBObject =
    grater[petParams].asDBObject(params)

  implicit def petToDBObject(c: pet): DBObject =
    grater[pet].asDBObject(c)
}
