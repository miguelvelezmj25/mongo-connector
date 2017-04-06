package edu.cmu.cs.mvelezce.mongo.connector

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/6/17.
  */
class CasbahTest extends FlatSpec {

  "CasbahConnector.connect()" should "return a non empty map" in {
    assert(Casbah.connect() != null)
  }
}
