enablePlugins(GatlingPlugin)

name := "blog-load-tests"
version := "0.1.0"

scalaVersion := "2.12.3"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % "test,it"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.3.0" % "test,it"
