package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSimulation extends Simulation {

  val baseUrl = s"http://localhost:8080";

  val httpConf = http.baseURL(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val routesPage = repeat(500) {
    exec(http("message route")
      .get("/routes/message"))
      .pause(2, 3)
  }

  val scn = scenario("Get the routes page")
    .exec(routesPage)



  setUp(scn.inject(atOnceUsers(5)).protocols(httpConf))
}
