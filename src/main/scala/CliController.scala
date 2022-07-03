import model.SocialScoreState.State
import service.InputProcessorService

import scala.collection.immutable.HashMap

object CliController extends  App {
  println("Enter First Command")
  io.Source.stdin.getLines().foldLeft(State(HashMap.empty[String, Int]))((currentState, newLine) => {
    InputProcessorService.processNewLine(currentState, newLine)
  })
}