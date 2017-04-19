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

  "ScalaMongoDriverConnector.findProjection(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findProjection(collection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findProjectionAscending(collection, projection, sort)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Class")
    projection.add("Method")
    projection.add("JavaLineNo")
    projection.add("JimpleLineNo")

    val sortBy: util.List[String] = new util.LinkedList[String]
    sortBy.add("Package")
    sortBy.add("Class")
    sortBy.add("Method")
    sortBy.add("JimpleLineNo")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findProjectionAscending(collection, projection, sortBy)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.findProjectionDescending(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findProjectionDescending(collection, projection, projection)
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

  "ScalaMongoDriverConnector.findProjectionFilterAscending(collection, projection, field, value, sort)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")
    val field = "Method"
    val value = "getCommandData"

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.findProjectionFilterAscending(collection, projection, field, value, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

}
