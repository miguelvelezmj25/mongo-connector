package edu.cmu.cs.mvelezce.mongo.connector.scaladriver

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/12/17.
  */
class ScalaMongoDriverConnectorTest extends FlatSpec {

  val database = "lotrack"
  val collection = "platypus"

  "Scala Mongo Driver" should "return a result" in {
    ScalaMongoDriverConnector.connect(database)
    ScalaMongoDriverConnector.query(collection)
    ScalaMongoDriverConnector.close()
  }
}
