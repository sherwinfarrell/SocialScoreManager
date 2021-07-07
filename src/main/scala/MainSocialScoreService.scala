import scala.collection.immutable.HashMap
import Utils.HelperUtils.{State}

object MainSocialScoreService extends  App {
  println("Enter First Command")
  io.Source.stdin.getLines().foldLeft(State(HashMap.empty[String, Int]))((currentState, newLine) => {
    InputProcessor.processNewLine(currentState, newLine)
  })
}