package controllers

import play.api._
import play.api.mvc._
import core._
import core.RetirementParameters
import core.EstimatedMonthlyExpenses
import core.InitialCapital

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def test(a: Int) = Action {
    val l = List(a,2,3)
    Ok(l.toString())
  }


  def simRetirement(cap: Double, exp: Double, yearsToER: Int, yearsToRA: Int) = Action {
    val params = RetirementParameters(
      InitialCapital(cap),
      EstimatedMonthlyExpenses(exp),
      YearsUntilEarlyRetirement(yearsToER),
      YearsUntilRetirementAge(yearsToRA)
    )

    val simulator = new simulator
    val result = simulator.simulateEarlyRetirement(params)
    Ok(result.simulatedRemainingCapital.d.toString)


  }


}