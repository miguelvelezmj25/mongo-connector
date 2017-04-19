package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util
import java.util.logging.{Level, Logger}

import edu.cmu.cs.mvelezce.mongo.connector.Connector
import edu.cmu.cs.mvelezce.mongo.connector.scaladriver.Helpers._
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.{Filters, Projections}
import org.mongodb.scala.model.Sorts._
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

  override def connect(database: String): Unit = {
    mongoClient = MongoClient()
    mongoDatabase = mongoClient.getDatabase(database)
  }

  override def close(): Unit = {
    mongoClient.close
  }

  override def find(collection: String): FindObservable[Document] = {
    val mongoCollection: MongoCollection[Document] = mongoDatabase.getCollection(collection)
    mongoCollection.find()
  }

  def findFilter(collection: String, field: String, value: String): util.List[String] = {
    val rawResult = find(collection)
    val filterResult = rawResult.filter(Filters.equal(field, value))

    val result: util.List[String] = new util.LinkedList[String]

    for(document <- filterResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  def findProjection(collection: String, projection: util.List[String]): util.List[String] = {
    val rawResult = find(collection)
    // Transform java list to scala sequences that is separated in multiple variables
    val projectionResult = rawResult.projection(Projections.include(asScalaBuffer(projection):_*))
    val result: util.List[String] = new util.LinkedList[String]

    for(document <- projectionResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  def findAscending(collection: String, projection: util.List[String], sort: util.List[String]): util.List[String] = {
    val rawResult = find(collection)
    // Transform java list to scala sequences that is separted in multiple variables
    val projectionResult = rawResult.projection(Projections.include(asScalaBuffer(projection):_*))
    val sortResult = projectionResult.sort(ascending(asScalaBuffer(sort):_*))

    val result: util.List[String] = new util.LinkedList[String]

    for(document <- sortResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  def findDescending(collection: String, projection: util.List[String], sort: util.List[String]): util.List[String] = {
    val rawResult = find(collection)
    // Transform java list to scala sequences that is separted in multiple variables
    val projectionResult = rawResult.projection(Projections.include(asScalaBuffer(projection):_*))
    val sortResult = projectionResult.sort(descending(asScalaBuffer(sort):_*))

    val result: util.List[String] = new util.LinkedList[String]

    for(document <- sortResult.results()) {
      result.add(document.toJson)
    }

    result
  }

  def getCollectionNames: util.List[String] = {
    val queryResult = mongoDatabase.listCollectionNames
    val result: util.List[String] = new util.LinkedList[String]

    for(collection <- queryResult.results()) {
      result.add(collection)
    }

    result
  }

}
