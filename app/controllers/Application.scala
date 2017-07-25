
package controllers

import play.api._
import play.api.mvc._
import play.mvc.Http

class Application extends Controller {

  val numbers = (1 to 100)

  def index() = Action{
    Redirect("/home" )
  }

  def home():Action[AnyContent] = Action{
    Ok(<H1>Home Page</H1>).as(HTML)
  }

  def toImplement() = TODO

  def notFound() = Action {
    NotFound //i assume this is correct it's hard to tell
  }

  def pageNotFound() = Action {
    NotFound(<H1>Page Not Found</H1>).as(HTML)
  }

  def badRequest() = Action {
    BadRequest("Something Bad Happened")
  }

  def oops = Action {
    InternalServerError("Oops")
  }

  def anyStatus() = Action{
    Status(488)("Strange Response Type")
  }

  def default(id :Long) = Action{
    Ok(id.toString)
  }

  def dynamic(id :Long) = Action{
    Ok(id.toString)
  }

  def set(input:String) = Action{
    Ok(input)
  }

  def list(id: Int) = Action {
    Ok(numbers(id).toString)
  }

  def getList() = Action {
    Ok(numbers.toString)
  }

  def optional(id: Option[String]) = Action{
    Ok(id.toString)
  }

  def reverseRoutingToHome() = Action {
    Redirect(routes.Application.home())
  }

  def reverseRoutingTopageNotFound() = Action{
    Redirect(routes.Application.pageNotFound())
  }

  def pageWithCookies() = Action{
    Ok("This page uses cookies").withCookies(
      Cookie("theme","blue"))
  }

  def editCookies() = Action{
    Ok("Set to red").discardingCookies(DiscardingCookie("theme"))withCookies(
      Cookie("theme","red"))
  }

  def discardCookies() = Action{
    Ok("This page removed the cookie").discardingCookies(DiscardingCookie("theme"))
  }

  def displayCookie() = Action { request =>
    request.cookies.get("theme").map{value => Ok(value.value)}.getOrElse(Ok("Cookie Jar Empty"))
  }
}

