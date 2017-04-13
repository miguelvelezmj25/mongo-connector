package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import java.util

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/12/17.
  */
class ScalaMongoDriverConnectorTest extends FlatSpec {

  val database = "lotrack"
  val collection = "platypus"

  "ScalaMongoDriverConnector.query(collection)" should "return a non null result" in {
    ScalaMongoDriverConnector.connect(database)
    assert(ScalaMongoDriverConnector.query(collection) != null)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.query(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.query(collection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.queryAscending(collection, projection, sort)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.queryAscending(collection, projection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.queryDescending(collection, projection)" should "return a non empty list of Json results" in {
    val projection: util.List[String] = new util.LinkedList[String]
    projection.add("Package")
    projection.add("Method")

    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.queryDescending(collection, projection, projection)
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

  "ScalaMongoDriverConnector.getCollectionsNames" should "return a non empty list of collection names" in {
    ScalaMongoDriverConnector.connect(database)
    val result = ScalaMongoDriverConnector.getCollectionNames
    assert(!result.isEmpty)
    ScalaMongoDriverConnector.close()
  }

}
