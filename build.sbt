name := "copycat-connect-exception-repro"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.atomix.copycat" % "copycat-server" % "1.0.1",
  "io.atomix.copycat" % "copycat-client" % "1.0.1",
  "io.atomix.catalyst" % "catalyst-netty" % "1.0.8",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0-RC1"
)