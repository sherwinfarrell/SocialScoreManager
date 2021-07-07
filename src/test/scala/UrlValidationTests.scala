import Utils.UrlChecker
import org.scalatest._

class UrlValidationTests extends FunSuite with Matchers {

  test("Valid URL 1 Check"){
    val testUrl = "https://www.google.com/2sh4shc"
    val result: Option[String] = UrlChecker.checkUrl(testUrl)
    assert(result === Some(testUrl))
  }

  test("Valid Url 2 Check"){
    val testUrl = "https://www.gggogg.ai"
    val result = UrlChecker.checkUrl(testUrl)
    assert(result === Some(testUrl))
  }

  test("Invalid Url 1 Check"){
    val testUrl = "https://www"
    val result = UrlChecker.checkUrl(testUrl)
    assert(result === None)
  }

  test("Invalid Url 2 Check"){
    val testUrl = "www.google.com/23432"
    val result = UrlChecker.checkUrl(testUrl)
    assert(result === None)
  }


  test("Invalid Url 3 Check"){
    val testUrl = "https://www.gggogg"
    val result = UrlChecker.checkUrl(testUrl)
    assert(result === None)
  }

}
