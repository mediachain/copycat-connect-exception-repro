name := "copycat-connect-exception-repro"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.atomix.copycat" % "copycat-server" % "1.0.1",
  "io.atomix.copycat" % "copycat-client" % "1.0.1",
  "io.atomix.catalyst" % "catalyst-netty" % "1.0.8",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "org.slf4j" % "slf4j-simple" % "1.7.21",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0-RC1"
)