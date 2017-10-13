name := "tasky"
version := "0.1.0"
scalaVersion := "2.12.3"
scalacOptions ++= Seq("-target:jvm-1.8", "-unchecked", "-deprecation", "-encoding", "utf8",
  "-Xfuture", "-Yno-adapted-args", "-Ywarn-dead-code", "-Ywarn-numeric-widen",
  "-Ywarn-value-discard", "-Ywarn-unused")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

