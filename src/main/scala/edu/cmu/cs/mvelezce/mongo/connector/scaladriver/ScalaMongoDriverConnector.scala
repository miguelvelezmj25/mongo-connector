package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util
import java.util.logging.{Level, Logger}

import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.{FindObservable, MongoClient, MongoCollection, MongoDatabase}

import scala.collection.JavaConverters._

import edu.cmu.cs.mvelezce.mongo.connector.Connector
import Helpers._

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

  def queryAscending(collection: String, projection: util.List[String], sort: util.List[String]): util.List[String] = {
    val rawResult = query(collection)
    // Transform java list to scala sequences that is separted in multiple variables
    val projectionResult = rawResult.projection(include(asScalaBuffer(projection):_*))
    val sortResult = projectionResult.sort(ascending(asScalaBuffer(sort):_*))

    val result: util.List[String] = new util.LinkedList[String]()

    for(document <- sortResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  def queryDescending(collection: String, projection: util.List[String], sort: util.List[String]): util.List[String] = {
    val rawResult = query(collection)
    // Transform java list to scala sequences that is separted in multiple variables
    val projectionResult = rawResult.projection(include(asScalaBuffer(projection):_*))
    val sortResult = projectionResult.sort(descending(asScalaBuffer(sort):_*))

    val result: util.List[String] = new util.LinkedList[String]()

    for(document <- sortResult.results()) {
      result.add(document.toJson)
    }

    result
  }

}
