package edu.cmu.cs.mvelezce.mongo

import java.util.logging.{Level, Logger}

//import org.mongodb.scala.bson.Document
//import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

/**
  * Created by miguelvelez on 4/5/17.
  */
object Connector {

  val mongoLogger = Logger.getLogger("org.mongodb.driver")
  mongoLogger.setLevel(Level.SEVERE)

//  def main(args: Array[String]) = {
//    connect()
//  }

//  def connect() = {
//    val client: MongoClient = MongoClient()
//    val database: MongoDatabase = client.getDatabase("lotrack")
//    val collection: MongoCollection[Document] = database.getCollection("Languagetool")
//
//    println("About to print")
//    collection.find().subscribe(
//      (user: Document) => println(user.toJson()),                         // onNext
//      (error: Throwable) => println(s"Query failed: ${error.getMessage}"), // onError
//      () => println("Done")                                               // onComplete
//    )
//    println("Printed")
//
//    client.close()
//  }

}
