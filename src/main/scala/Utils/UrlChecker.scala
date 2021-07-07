package Utils
import org.apache.commons.validator.routines.UrlValidator

object UrlChecker {

  def checkUrl(urlString: String): Option[String] = {
    val urlValidator = new UrlValidator()
    urlValidator.isValid(urlString) match {
      case true =>
        Some(urlString)
      case false => None
    }
  }
}
