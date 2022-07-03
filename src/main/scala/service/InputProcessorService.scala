package service

import model.SocialScoreState.{State, StateException}
import model.{Add, Export, Remove}

import scala.util.{Failure, Success, Try}

object InputProcessorService {

  def processNewLine(state: State, line: String): State = {
    val splittedLine: List[String] = line.split(" ").toList

    val newState: State = splittedLine(0).toLowerCase match {

      case "" =>
        println("Nothing was passed in !!")
        state

      case "exit" => System.exit(0)
        state

      case "add" if splittedLine.length == 3 =>
        val valid = UrlService.checkUrl(splittedLine(1))
        valid match {
          case Some(url) =>
            CommandService.addURL(Add(url, Try(splittedLine(2).toInt), state)) match {
              case Left(StateException(state, exception)) => println(s"There was an exception: ${exception.toString}")
                state
              case Right(state) => state
            }
          case None =>
            println("URl seems to be wrong")
            state
        }

      case "remove" if splittedLine.length == 2 =>
        UrlService.checkUrl(splittedLine(1)) match {
          case Some(url) =>
            CommandService.removeUrl(Remove(url, state)) match {
              case Left(StateException(state, exception)) =>
                println(s"There was an exception: ${exception.toString}")
                state
              case Right(state) => state
            }
          case None => println("URl seems to be wrong")
            state
        }

      case "export" => CommandService.createCsv(Export(state)) match {
        case Success(_) => state
        case Failure(e) => println(s"An error Occured: ${e.getMessage}")
          state
      }

      case _ => println("Command Seems to be Wrong!!")
        state
    }
    println("Waiting for Next Command")
    newState
  }
}
