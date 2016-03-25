import sbt.Keys._

lazy val root = (project in file(".")).
  settings(
    name := "akka-debug",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.4.2" withSources() withJavadoc(),
      "com.typesafe.akka" %% "akka-testkit" % "2.4.2" withSources() withJavadoc(),
      "com.typesafe.akka" %% "akka-stream" % "2.4.2" withSources() withJavadoc(),
      "org.scalactic" %% "scalactic" % "2.2.6" withSources() withJavadoc(),
      "org.scalatest" %% "scalatest" % "2.2.6" % "test"  withSources() withJavadoc()
    ),
    initialCommands in console := "import akka._;"
      + "import akka.stream._;"
      + "import akka.stream.scaladsl._;"
      + "import akka.actor.Status._;"
      + "import scala.util.Try._;"
      + "import akka.actor._;"


  )