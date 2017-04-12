name := "mongo-connector"

version := "0.1.0-SNAPSHOT"

organization := "edu.cmu.cs.mvelezce"

scalaVersion := "2.12.1"

publishMavenStyle := true

publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath + "/.m2/repository")))

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.0.0"

//libraryDependencies += "org.mongodb" %% "casbah" % "3.1.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"

