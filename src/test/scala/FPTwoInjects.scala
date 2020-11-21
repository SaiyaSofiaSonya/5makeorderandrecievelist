import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt

trait FPTwoInjects extends Simulation {

  def loader(firstScenario: ScenarioBuilder): SetUp = {
    val httpProtocol = http.baseUrl("http://185.233.0.230:3000/")

  //Checking the stair with max perfomance
 /*   setUp(firstScenario.inject(constantUsersPerSec(12) during(65 minutes)).
      throttle(reachRps(12) in (5 minutes),
        holdFor (60 minutes))
        .protocols(httpProtocol),
        ).maxDuration(65 minutes)*/

    //Steps. To count max intensity by chairs
    setUp(firstScenario.inject(constantUsersPerSec(45) during(30 minutes)).
           throttle(
             reachRps(15)  in (1 minutes), holdFor (10 minutes),
             reachRps(30)  in (1 minutes), holdFor (10 minutes),
             reachRps(45) in (1 minutes), holdFor (10 minutes),
      //       reachRps(60) in (1 minutes), holdFor (10 minutes),
      //       reachRps(75) in (1 minutes), holdFor (10 minutes),
      //       reachRps(90) in (1 minutes), holdFor (10 minutes),
       //      reachRps(105) in (1 minutes), holdFor (10 minutes),
       //     reachRps(120) in (1 minutes), holdFor (10 minutes),
           ).protocols(httpProtocol),
           ).maxDuration(30 minutes) //Мааксимальное время до выполения всего скрипта?
     }
// Расчет максимального для нецелых значений
      /*
        val stepsOne = mutable.MutableList[OpenInjectionStep]()
        val stepsTwo = mutable.MutableList[OpenInjectionStep]()

        def perMinute(rate: Double): Double = rate / 60

        val durationsStage = (15 minutes);
        val stages = 15;
        val ratePerStage1 = 240;
        val ratePerStage2 = 30;

        for (s <- 1 to stages) {
          val rate = s * ratePerStage1;
          stepsOne+= (constantUsersPerSec(perMinute(rate)) during (durationsStage));
        }
    for (s <- 1 to stages) {
      val rate = s * ratePerStage2;
      stepsTwo+= (constantUsersPerSec(perMinute(rate)) during (durationsStage));
    }

        setUp(
       firstScenario.inject(stepsOne.toList).protocols(httpProtocol),
          secondScenario.inject(stepsTwo.toList).protocols(httpProtocol)
        )
*/
}
