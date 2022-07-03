package service

import model.SocialScoreState.{State, StateException, UrlScore}
import model.{Add, Export, Remove}

import scala.collection.immutable.HashMap
import scala.util.{Failure, Success, Try}

object CommandService {

  def addURL(add: Add): Either[StateException, State] = {
    val score: Int = add.score match {
      case Success(value) => value
      case Failure(e) =>
        print(s"There was a failure ${e.getMessage}")
        return Left(StateException(add.state, e))
    }
    val newState = State(add.state.urlScore + (add.url -> score))
    Right(newState)
  }

  def removeUrl(remove: Remove): Either[StateException, State] = {
    val newScoreMap = Try(remove.state.urlScore.filter(x => x._1 != remove.url))
    val newState: Either[StateException, State] = newScoreMap match {
      case Success(scoreMap: HashMap[String, Int]) => Right(State(scoreMap))
      case Failure(e) => Left(StateException(remove.state, e))
    }
    newState
  }

  def createCsv(ex: Export): Try[List[UrlScore]] = {
    if (ex.state.urlScore.isEmpty) {
      return Failure(new NoSuchElementException("No Url or Social Scores have been added yet."))
    }
    val keySet: Set[String] = ex.state.urlScore.keySet.map(key => {
      val splitKey = key.split('/')
      splitKey(0) + "//" + splitKey(2)
    })
    val keyAndScore: Try[List[UrlScore]] = Try(keySet.map(key => {
      val aggregateScore = ex.state.urlScore.keySet.filter(x => x.contains(key)).
        foldLeft(0)((x, y) => x + ex.state.urlScore.getOrElse(y, 0))
      println(key + "," + aggregateScore)
      UrlScore(key, aggregateScore.toString)
    }).toList)
    keyAndScore
  }
}
