name := "sentiment-analyzer"
description := "A demo application to showcase sentiment analysis using Stanford CoreNLP and Scala"
version := "0.1.0"

scalaVersion := "2.12.3"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp"))
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
