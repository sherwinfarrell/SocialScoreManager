import java.io.ByteArrayInputStream
import org.scalatest.{FunSpec, Matchers}
import service.InputProcessorService

import scala.collection.immutable.HashMap

class MainSocialStoreServiceTest extends FunSpec with Matchers {

  describe("Main Social Store Service Runs correctly"){
    it("System Inputs are Picked up and Processed Correctly Test"){
      println("Enter First Command")
      val in = new ByteArrayInputStream("ADD https://www.google.com/2343 20".getBytes)
      System.setIn(in)
      val result: State = io.Source.stdin.getLines().foldLeft(State(HashMap.empty[String, Int]))((currentState, newLine) => {
        InputProcessorService.processNewLine(currentState, newLine)
      })
      assert(result.urlScore.head._1 === "https://www.google.com/2343")
      assert(result.urlScore.head._2 === 20)
    }
    it("System Inputs are Picked up and Processed InCorrectly Test"){
      println("Enter First Command")
      val in = new ByteArrayInputStream("ADD".getBytes)
      System.setIn(in)
      val result: State = io.Source.stdin.getLines().foldLeft(State(HashMap.empty[String, Int]))((currentState, newLine) => {
        InputProcessorService.processNewLine(currentState, newLine)
      })
      assert(result === State(HashMap.empty[String, Int]))
    }
  }

}
