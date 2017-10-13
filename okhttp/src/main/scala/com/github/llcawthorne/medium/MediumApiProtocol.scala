package com.github.llcawthorne.medium

import DomainObjects._
import spray.json.DefaultJsonProtocol

object MediumApiProtocol extends DefaultJsonProtocol {

  implicit val userFormat = jsonFormat5(User)

  implicit val postRequestFormat = jsonFormat7(PostRequest)

  implicit val postFormat = jsonFormat11(Post)

}
