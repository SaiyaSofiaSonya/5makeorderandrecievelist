import FPTwoMethod._
import io.gatling.core.Predef.{Simulation, scenario}
import io.gatling.core.structure.ScenarioBuilder

class FPTwoSimulation extends Simulation with FPTwoInjects {

  def scenarioOne: ScenarioBuilder = scenario("scenarioOne").exec(firstChain)
  loader(scenarioOne)
}
