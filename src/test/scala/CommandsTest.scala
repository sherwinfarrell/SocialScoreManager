import Utils.HelperUtils
import Utils.HelperUtils.{Add, Export, Remove, State, StateException}
import org.scalamock.scalatest.MockFactory
import org.scalatest.Inside.inside
import org.scalatest.{FunSpec, Matchers}

import scala.collection.immutable.HashMap
import scala.util.{Success, Try}

class CommandsTest extends FunSpec with Matchers with MockFactory {

    describe("Testing the Add Command"){
      it("Valid Call to AddUrl Must Return Valid State"){
        val url = "https://www.google.com/234"
        val score = Try("10".toInt)
        val state = State(HashMap.empty[String, Int])
        val addClass =  Add(url = url, score=score, state)
        val result: Either[HelperUtils.StateException, State] = Commands.addURL(addClass)

        val returnedState = State(HashMap(url -> 10))
        assert(result === Right(returnedState))
      }
      it("Invalid Call must return Left with Exception and Same State"){
        val url = "https://www.google.com/234"
        val score = Try("sdf".toInt)
        val state = State(HashMap.empty[String, Int])
        val addClass =  Add(url = url, score=score, state)
        val result: Either[HelperUtils.StateException, State] = Commands.addURL(addClass)

        inside(result) { case Left(StateException(s,e)) =>
          s should be (state)
        case Right(s) => s should be (state)
        }
      }
    }

  describe("Testing the Export Command"){
    it("It should create a csv file"){
      val expectedResult =
      Commands.createCsv(Export(State(HashMap("https://www.google.com/23423432"->20)))) match {
        case Success(value) => assert(value.head.score === "20")
          assert(value.head.url === "https://www.google.com")
      }
    }

    it("It Should throw an error"){
      val result = Commands.createCsv(Export(State(HashMap.empty[String,Int])))
      result.isFailure should be (true)
      }
    }


  describe("Testing the Remove Command"){
    it("It should remove the url from the state") {
      val url = "https://www.google.com/12345"
      val state = State(HashMap(url -> 20, "https://www.google.com/abcde" -> 40))
      val input = Remove(url, state)
      val result = Commands.removeUrl(input)
      val expectedState = State(HashMap("https://www.google.com/abcde" -> 40))
      inside(result) {
        case Left(StateException(s, e)) => s should be(state)
        case Right(s) => s should be(expectedState)

      }
    }
    it("It handles empty state by returning the same state") {
      val url = "https://www.google.com/12345"
      val state = State(HashMap.empty[String, Int])
      val input = Remove(url, state)
      val result = Commands.removeUrl(input)
      val expectedState = State(HashMap("https://www.google.com/abcde" -> 40))
      inside(result) {
        case Left(StateException(s, e)) => s should be(state)
        case Right(s) => s should be(state)
      }
    }
  }
}
