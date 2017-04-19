package edu.cmu.cs.mvelezce.mongo.connector

import org.mongodb.scala.FindObservable
import org.mongodb.scala.bson.collection.immutable.Document

/**
  * Created by mvelezce on 4/12/17.
  */
trait Connector {
  def connect(database: String)

  def find(collection: String): FindObservable[Document]

  def close()
}
