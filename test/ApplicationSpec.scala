
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      status(route(app, FakeRequest(GET, "/boum")).get) must equalTo(NOT_FOUND)
    }

    "render the index page" in new WithApplication{
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) must equalTo(SEE_OTHER)
    }

    "render the home page" in new WithApplication{
      val home = route(app, FakeRequest(GET, "/home")).get
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("Home Page")

    }

    "render the todo page" in new WithApplication{
      val stillToDo = route(app, FakeRequest(GET, "/toImplement")).get
      status(stillToDo) must equalTo(NOT_IMPLEMENTED)
    }

    "render the not found page" in new WithApplication{
      val notFound = route(app, FakeRequest(GET, "/notFound")).get
      status(notFound) must equalTo(NOT_FOUND)
    }

    "render the page not found page" in new WithApplication{
      val pageNotFound = route(app, FakeRequest(GET, "/pageNotFound")).get
      status(pageNotFound) must equalTo(NOT_FOUND)
      contentType(pageNotFound) must beSome.which(_ == "text/html")
      contentAsString(pageNotFound) must contain("Page Not Found")
    }

    "render the bad request page" in new WithApplication{
      val badRequest = route(app, FakeRequest(GET, "/badRequest")).get
      status(badRequest) must equalTo(BAD_REQUEST)
    }

    "render the oops page" in new WithApplication{
      val oops = route(app, FakeRequest(GET, "/oops")).get
      status(oops) must equalTo(INTERNAL_SERVER_ERROR)
    }

    "render the anyStatus page" in new WithApplication{
      val anyStatus  = route(app, FakeRequest(GET, "/anyStatus")).get
      val strangeResponse :Int = 488
      status(anyStatus) must equalTo(strangeResponse)
    }

    "render the default page" in new WithApplication{
      val default  = route(app, FakeRequest(GET, "/default")).get
      status(default) must equalTo(OK)
      contentAsString(default) must contain("0")
    }

    "render the static page" in new WithApplication{
      val static  = route(app, FakeRequest(GET, "/static/all")).get
      status(static) must equalTo(OK)
      contentAsString(static) must contain((1 to 100).toList.toString)
    }

    "render the dynamic page" in new WithApplication{
      val dynamic  = route(app, FakeRequest(GET, "/dynamic/1")).get
      status(dynamic) must equalTo(OK)
      contentAsString(dynamic) must contain("1")
    }

    "render the page not found for invalid input" in new WithApplication{
      val dynamic  = route(app, FakeRequest(GET, "/dynamic/a")).get
      status(dynamic) must equalTo(NOT_FOUND)
    }

    "render the set page" in new WithApplication{
      val set  = route(app, FakeRequest(GET, "/set")).get
      status(set) must equalTo(OK)
      contentAsString(set) must contain("hello")
    }

    "render the optional page with input" in new WithApplication{
      val optional  = route(app, FakeRequest(GET, "/optional?id=hello")).get
      status(optional) must equalTo(OK)
      contentAsString(optional) must contain("hello")
    }

    "render the optional page without input" in new WithApplication{
      val optional  = route(app, FakeRequest(GET, "/optional")).get
      status(optional) must equalTo(OK)
      contentAsString(optional) must contain("None")
    }

    "render the reverse routing page" in new WithApplication{
      val reverseRouting  = route(app, FakeRequest(GET, "/reverseRouting")).get
      status(reverseRouting) must equalTo(SEE_OTHER)
    }


  }
}
