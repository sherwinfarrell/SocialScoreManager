import org.scalatest.{FunSpec, Matchers}
import service.InputProcessorService

import scala.collection.immutable.HashMap

class InputProcessorServiceTest  extends FunSpec with Matchers {

  describe("Tests for InputProcessor"){
    describe("Testing Add Commands"){
      it("ProcessNewLine Function correctly returns New State on correct Add Command"){
        val newLine = "ADD https://www.googl.com/2343 20"
        val state = HashMap.empty[String, Int]
        val newState = HashMap("https://www.googl.com/2343" -> 20)
        val result: State = InputProcessorService.processNewLine(State(state), newLine)
        assert(result == State(newState))
      }
      it("ProcessNewLine returns old state on wrong Add command"){
        val newLine = "ADD https://www.googl.com/2343"
        val state = HashMap.empty[String, Int]
        val result: State = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
      it("ProcessNewLine returns old state on wrong Add command 2"){
        val newLine = "ADD https://www.googl.com/2343 dsfds"
        val state = HashMap.empty[String, Int]
        val result: State = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
      it("ProcessNewLine returns old state on wrong Add command 3"){
        val newLine = "ADD"
        val state = HashMap("https://www.google.com/234" -> 20)
        val result: State = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
    }

    describe("Testing Remove command"){
      it("ProcessNewLine function correctly returns new state on correct command"){
            val newLine = "REMOVE https://www.google.com"
            val state = HashMap("https://www.google.com" -> 20)
            val result = InputProcessorService.processNewLine(State(state), newLine)
            assert(result === State(HashMap.empty[String, Int]))
      }

      it("ProcessNewLine function correctly returns old state on incorrect command 1"){
        val newLine = "REMOVE https://www.google.com 20"
        val state = HashMap("https://www.google.com" -> 20)
        val result = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
      it("ProcessNewLine function correctly returns old state on incorrect command 2"){
        val newLine = "REMOVE https:wwwwwww"
        val state = HashMap("https://www.google.com" -> 20)
        val result = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
      it("ProcessNewLine function correctly returns old state on incorrect command 3"){
        val newLine = "REMOVE"
        val state = HashMap("https://www.google.com" -> 20)
        val result = InputProcessorService.processNewLine(State(state), newLine)
        assert(result === State(state))
      }
    }

    describe("Testing Export Command"){
      it("ProcessNewLine function correctly returns new state when export command is correct"){
        val newLine = "EXPORT"
        val state = HashMap("https://www.google.com" -> 20)
        val result = InputProcessorService.processNewLine(State(state), newLine)
        assert(result == State(state))
      }
    }

    describe("Testing Miscellaneous Commands"){
      it("Testing ProcessNewLine with Empty NewLine"){
        val newLine = ""
        val state = State(HashMap("https://www.google.com/234" -> 20))
        val result = InputProcessorService.processNewLine(state, newLine)
        result should be (state)
      }
    }
  }
}
