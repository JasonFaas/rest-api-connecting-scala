name := "JasonAttemptCopySbtExample"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc(),

  "org.scalaj" % "scalaj-http_2.11" % "2.3.0" % "test" withSources(),

  "org.apache.httpcomponents" % "httpcore" % "4.4.5" % "test" withSources(),
  "org.apache.httpcomponents" % "httpclient" % "4.5.2" % "test" withSources()

)
