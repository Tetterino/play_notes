name := """notes"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies ++= Seq(
    "com.h2database" % "h2" % "1.4.191",
    "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0-M2",
    "com.typesafe.play" %% "play-slick" % "5.0.0-M2",
    "mysql" % "mysql-connector-java" % "8.0.16"
)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
