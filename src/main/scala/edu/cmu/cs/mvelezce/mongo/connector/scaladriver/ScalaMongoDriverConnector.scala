package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util
import java.util.logging.{Level, Logger}

import Helpers._
import org.mongodb.scala.model.Projections._
import edu.cmu.cs.mvelezce.mongo.connector.Connector
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{FindObservable, MongoClient, MongoCollection, MongoDatabase}

import scala.collection.JavaConverters._

/**
  * Created by miguelvelez on 4/5/17.
  */
object ScalaMongoDriverConnector extends Connector {

  private val mongoLogger = Logger.getLogger("org.mongodb.driver")
  mongoLogger.setLevel(Level.SEVERE)

  private var mongoClient: MongoClient = _
  private var mongoDatabase: MongoDatabase = _

  override def connect(database: String) = {
    mongoClient = MongoClient()
    mongoDatabase = mongoClient.getDatabase(database) //"lotrack")
  }

  override def close() = {
    mongoClient.close()
  }

  override def query(collection: String): FindObservable[Document] = {
    val mongoCollection: MongoCollection[Document] = mongoDatabase.getCollection(collection)
    mongoCollection.find()
  }

  def query(collection: String, projection: util.List[String]): util.List[String] = {
    val rawResult = query(collection)
    // Transform java list to scala sequences that is separted in multiple variables
    val projectionResult = rawResult.projection(include(asScalaBuffer(projection):_*))
    val result: util.List[String] = new util.LinkedList[String]()

    for(document <- projectionResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  //        for(field <- fields.asScala) {
  //          queryFields.put(field, 1)
  //        }
  //
  //        val querySort = MongoDBObject()
  //
  //        for(field <- sortBy.asScala) {
  //          querySort.put(field, 1)
  //        }
  //
  //        val queryResult = collection.find(MongoDBObject.empty, queryFields).sort(querySort)

  //        println(mongoCollection.find.first().results())
  //    val p = mongoCollection.find()
  //    println(p.headResult())
  //    val pp = p.projection(include("Package"))
  //    println(pp.headResult())
  //        println(mongoCollection.find(include("Package")).first().headResult().toJson())
  //        mongoCollection.find.first().printResults()
  //        mongoCollection.find.first().printHeadResult()



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
