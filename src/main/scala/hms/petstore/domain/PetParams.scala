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

package hms.petstore.domain

import com.mongodb.casbah.Imports.ObjectId

case class PetParams(_id: Option[ObjectId] = None, name: Option[String] = None,status: Option[String] = None,
                     category: Option[Category] = None,photo_urls: Option[Array[String]] = None,
                     tags: Option[Array[Tagd]] = None)


