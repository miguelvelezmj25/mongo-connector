package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/12/17.
  */
class ScalaMongoDriverConnectorTest extends FlatSpec {

  val database = "lotrack"
  val collection = "platypus"

  "ScalaMongoDriverConnector.find(collection)" should "return a non null result" in {
    ScalaMongoDriverConnector.connect(database)
    assert(ScalaMongoDriverConnector.find(collection) != null)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findProject(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findProjection(collection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findAscending(collection, projection, sort)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findAscending(collection, projection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findDescending(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findDescending(collection, projection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.getCollectionsNames" should "return a non empty list of collection names" in {
    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.getCollectionNames
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findFilter(collection, field, value)" should "return a non empty list of Json results" in {
    val field = "Method"
    val value = "getCommandData"

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findFilter(collection, field, value)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

}
