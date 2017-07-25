
package controllers

import play.api.mvc._

class Application extends Controller {

  private val numbers: List[Int] = (1 to 100).toList

  def index(): Action[AnyContent] = Action{
    Redirect("/home" )
  }

  def home():Action[AnyContent] = Action{
    Ok(<H1>Home Page</H1>).as(HTML)
  }

  def stillToImplement(): Action[AnyContent] = TODO

  def notFound(): Action[AnyContent] = Action {
    NotFound //i assume this is correct it's hard to tell
  }

  def pageNotFound(): Action[AnyContent] = Action {
    NotFound(<H1>Page Not Found</H1>).as(HTML)
  }

  def badRequest(): Action[AnyContent] = Action {
    BadRequest("Something Bad Happened")
  }

  def oops(): Action[AnyContent] = Action {
    InternalServerError("Oops")
  }

  def anyStatus(): Action[AnyContent] = Action{
    val strangeResponse :Int = 488
    Status(strangeResponse)("Strange Response Type")
  }

  def default(id :Long): Action[AnyContent] = Action{
    Ok(id.toString)
  }

  def dynamic(id :Long): Action[AnyContent] = Action{
    Ok(id.toString)
  }

  def set(input:String): Action[AnyContent] = Action{
    Ok(input)
  }

  def list(id: Int): Action[AnyContent] = Action {
    Ok(numbers(id).toString)
  }

  def displayList(): Action[AnyContent] = Action {
    Ok(numbers.toString)
  }

  def optional(id: Option[String]): Action[AnyContent] = Action{
    Ok(id.toString)
  }

  def reverseRoutingToHome(): Action[AnyContent] = Action {
    Redirect(routes.Application.home())
  }

  def reverseRoutingToPageNotFound(): Action[AnyContent] = Action{
    Redirect(routes.Application.pageNotFound())
  }

  def pageWithCookies(): Action[AnyContent] = Action{
    Ok("This page uses cookies").withCookies(
      Cookie("theme","blue"))
  }

  def editCookies(): Action[AnyContent] = Action{
    Ok("Set to red").discardingCookies(
      DiscardingCookie("theme"))withCookies Cookie("theme","red")
  }

  def discardCookies(): Action[AnyContent] = Action{
    Ok("This page removed the cookie").discardingCookies(
      DiscardingCookie("theme"))
  }

  def displayCookie(): Action[AnyContent] = Action { request =>
    request.cookies.get("theme").map{
      value => Ok(value.value)
    }.getOrElse(Ok("Cookie Jar Empty"))
  }

  def createSession(): Action[AnyContent] = Action{
    Ok("Start A Session").withSession(
      "connected"->"user@provider.com")
  }

  def addDataToSession(): Action[AnyContent] = Action{ request =>
    Ok("Add Data to the session").withSession(
        request.session + ("theme" -> "blue"))
  }

  def removeDataFromSession(): Action[AnyContent] = Action{ request =>
    Ok("Add Data to the session").withSession(
      request.session - "theme")
  }

  def readSession(): Action[AnyContent] = Action{ request =>
    request.session.get("theme").map{
      value => Ok("hello " + value)
    }.getOrElse(Ok("not connected"))
  }

  def readSessionData(): Action[AnyContent] = Action{ request =>
    Ok(request.session.data.toString())
  }

  def setFlash(): Action[AnyContent] = Action{
    Redirect("/flashing/getData").flashing(
      "Data" -> "This was passed via flashing"
    )
  }

  def fetchFlashData(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      request.flash.get("Data").map{
        value => Ok(value)
      }.getOrElse(Ok("no data"))
  }
}