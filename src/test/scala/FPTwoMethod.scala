import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.http

object FPTwoMethod {

  val mainPage: ChainBuilder = exec(http("mainPage")
    .get("/")
    .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .header("user-agent",
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36")
  )

  val emailReceiveOrderList = csv("email.csv").circular
  val sendOrder: ChainBuilder = feed(emailReceiveOrderList)
    .exec(http("sendOrder")
      .post("/api/orders")
      .formParam("rowBody","${rowBodyString}")
      .header("user-agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36"))


   val receiveOrderGet: ChainBuilder = exec(http("receiveOrderGet")
    .get("/api/orders")
    .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .header("user-agent",
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36")
  )

  val receiveOrderPost: ChainBuilder = feed(emailReceiveOrderList)
    .exec(http("receiveOrderPost")
      .post("/api/orders")
      .formParam("rowBody","${rowBodyString}")
      .header("user-agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36"))

// Scenario 1
  val firstChain: ChainBuilder =
    group("firstChain"){
      exec("mainPage")
      exec(sendOrder)
      exec(receiveOrderGet)
      exec(receiveOrderPost)
    }
}


