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


case class Pet(_id: ObjectId = new ObjectId,
               name: String,
               status: String,
               category: category,
               photo_urls: Array[String],
               tags: Array[tagd]

                )

case class category(_id: Int, name: String)

case class tagd(_id: Int, name: String)