package edu.cmu.cs.mvelezce.mongo.connector

/**
  * Created by mvelezce on 4/12/17.
  */
trait Connector {
  def connect(database: String)

  def query(collection: String)

  def close()
}
