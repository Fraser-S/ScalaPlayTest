
package controllers

import play.api._
import play.api.mvc._

class Application extends Controller {
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
}

