package Utils
import scala.collection.immutable.HashMap
import scala.util.Try

object HelperUtils {

  case class State(urlScore: HashMap[String, Int])
  case class StateException(state: State, exception: Throwable)
  case class UrlScore(url: String, score: String)

  case class Add(url: String, score: Try[Int], state: State)
  case class Remove(url: String, state: State)
  case class Export(state: State)
}
