package edu.cmu.cs.mvelezce.mongo.connector

import java.util

import org.scalatest.FlatSpec

/**
  * Created by mvelezce on 4/6/17.
  */
class CasbahTest extends FlatSpec {

  "CasbahConnector.connect(app, query)" should "return a non empty map" in {
    val fields: util.List[String] = new util.ArrayList[String]
    fields.add("Package")
    fields.add("Class")
    fields.add("Method")
    fields.add("JimpleLineNo")

    val sortBy = fields
    val result = Casbah.connect("platypus", fields, sortBy)

    assert(result.size() != 0)
//    println(a)
  }
}
