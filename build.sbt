name := "ApiExample"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "io.rest-assured" % "scala-support" % "3.3.0",
  "org.scalatest" %% "scalatest" % "3.0.5",
  "org.pegdown" % "pegdown" % "1.4.2",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.9.8",
  "org.json4s" %% "json4s-native" % "3.6.4",
  "org.json4s" %% "json4s-jackson" % "3.6.4",
)