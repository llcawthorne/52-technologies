package com.github.llcawthorne.fitman

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FitmanMain extends FitmanServer

class FitmanServer extends HttpServer {

  override protected def defaultFinatraHttpPort: String = ":8080"
  override protected def defaultTracingEnabled: Boolean = false
  override protected def defaultHttpServerName: String = "FitMan"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[HelloController]
      .add[WeightResource]
  }
}

class HelloController extends Controller {

  get("/hello") {request: Request =>
    "Fitman says hello"
  }

}
