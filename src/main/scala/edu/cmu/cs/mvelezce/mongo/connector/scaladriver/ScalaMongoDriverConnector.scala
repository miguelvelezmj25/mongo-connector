package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util.logging.{Level, Logger}

import Helpers._
import edu.cmu.cs.mvelezce.mongo.connector.Connector
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

/**
  * Created by miguelvelez on 4/5/17.
  */
object ScalaMongoDriverConnector extends Connector {

  private val mongoLogger = Logger.getLogger("org.mongodb.driver")
  mongoLogger.setLevel(Level.SEVERE)

  private var mongoClient: MongoClient = _
  private var mongoDatabase: MongoDatabase = _

  override def connect(database: String): Unit = {
    mongoClient = MongoClient()
    mongoDatabase = mongoClient.getDatabase(database) //"lotrack")
  }

  override def close(): Unit = {
    mongoClient.close()
  }

  override def query(collection: String): Unit = {
    val mongoCollection: MongoCollection[Document] = mongoDatabase.getCollection(collection)//"Languagetool")

        println(mongoCollection.find.first().results())
        println(mongoCollection.find.first().headResult().toJson())
        mongoCollection.find.first().printResults()
        mongoCollection.find.first().printHeadResult()
  }

//


//    val queryResult = collection.find().first().printResult
//    println()
//
//    val result = new util.LinkedList[util.Map[String, String]]
//
//    for(document <- queryResult) {
//      val hold: util.Map[String, String] = new util.HashMap[String, String]
//
//      document.foreach { entry =>
//        val key = entry._1
//        var key1 = ""
//
//        if(key == null) {
//          key1 = "null"
//        }
//        else {
//          key1 = key.toString
//        }
//
//        val value = entry._2
//        var value1 = ""
//
//        if(value == null) {
//          value1 = "null"
//        }
//        else {
//          value1 = value.toString
//        }
//
//        hold.put(key1, value1)
//      }
//
//      result.add(hold)
//    }
//    println(result)
//    println("Printed")

}
