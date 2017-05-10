
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class parti2b extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("text/css,*/*;q=0.1")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")



	val scn = scenario("parti2b")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/webjars/github-com-twbs-bootstrap/v3.3.7/docs/dist/css/bootstrap-theme.css"),
            http("request_2")
			.get("/webjars/bootstrap/4.0.0-alpha.6/dist/css/bootstrap.css")))
		.pause(7)
		.exec(http("request_3")
			.post("/loguearse")
			.headers(headers_0)
			.formParam("usuario", "Hugo")
			.formParam("password", "password")
			.resources(http("request_4")
			.get("/webjars/github-com-twbs-bootstrap/v3.3.7/docs/dist/css/bootstrap-theme.css"),
            http("request_5")
			.get("/webjars/bootstrap/4.0.0-alpha.6/dist/css/bootstrap.css")))
		.pause(5)
		.exec(http("request_6")
			.get("/crearPropuesta")
			.headers(headers_0)
			.resources(http("request_7")
			.get("/webjars/github-com-twbs-bootstrap/v3.3.7/docs/dist/css/bootstrap-theme.css"),
            http("request_8")
			.get("/webjars/bootstrap/4.0.0-alpha.6/dist/css/bootstrap.css")))
		.pause(10)
		.exec(http("request_9")
			.post("/anadirPropuesta")
			.headers(headers_0)
			.formParam("titulo", "NuevaPropuesta")
			.formParam("categoria", "1")
			.formParam("propuesta", "Esta es la nueva propuesta")
			.resources(http("request_10")
			.get("/webjars/github-com-twbs-bootstrap/v3.3.7/docs/dist/css/bootstrap-theme.css"),
            http("request_11")
			.get("/webjars/bootstrap/4.0.0-alpha.6/dist/css/bootstrap.css")))

	setUp(scn.inject(rampUsers(100) over(100 seconds))).protocols(httpProtocol)

}