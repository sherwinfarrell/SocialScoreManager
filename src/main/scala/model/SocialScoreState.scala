package model

import scala.collection.immutable.HashMap

object SocialScoreState {
  case class State(urlScore: HashMap[String, Int])
  case class StateException(state: State, exception: Throwable)
  case class UrlScore(url: String, score: String)
}
