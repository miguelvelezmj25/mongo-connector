package edu.cmu.cs.mvelezce.mongo.connector

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/6/17.
  */
class CasbahTest extends FlatSpec {

  "CasbahConnector.connect(app, query)" should "return a non empty map" in {
    val a = Casbah.connect("platypus")
    assert(a.size() != 0)
//    println(a)
  }
}
