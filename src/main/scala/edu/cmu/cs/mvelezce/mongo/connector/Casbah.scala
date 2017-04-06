package edu.cmu.cs.mvelezce.mongo.connector

import java.util
import java.util.logging.{Level, Logger}

import com.mongodb.casbah.{MongoClient, MongoCollection, MongoDB}

/**
  * Created by miguelvelez on 4/5/17.
  */
object Casbah {

  val mongoLogger: Logger = Logger.getLogger("org.mongodb")
  mongoLogger.setLevel(Level.SEVERE)

  def connect(): util.LinkedList[util.HashMap[String,String]] = {
    val client: MongoClient = MongoClient("localhost", 27017)
    val database: MongoDB = client("lotrack")
    val collection: MongoCollection = database("Languagetool")
    val queryResult = collection.findOne()
    val result = new util.LinkedList[util.HashMap[String,String]]

    for(document <- queryResult) {
      val hold = new util.HashMap[String,String]

      document.toMap.entrySet.forEach { entry =>
        val key = entry.getKey
        var key1 = ""

        if(key == null) {
          key1 = "null"
        }
        else {
          key1 = key.toString
        }

        val value = entry.getValue
        var value1 = ""

        if(value == null) {
          value1 = "null"
        }
        else {
          value1 = value.toString
        }

        hold.put(key1, value1)
      }

      println(hold)
      result.add(hold)
    }

    client.close()
    result
  }

}
