package model

import model.SocialScoreState.State

import scala.util.Try

sealed trait Command {
}


case class Add(url: String, score: Try[Int], state:  State) extends Command
case class Remove(url: String, state: State) extends Command
case class Export(state: State) extends Command