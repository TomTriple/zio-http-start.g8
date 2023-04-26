package $package$

import zio._
import zio.http._

object MainApp extends ZIOAppDefault {

  val app: App[Any] = 
    Http.collect[Request] {
      case Method.GET -> !! / "text" => Response.text("hello world")
    }

  override val run =
    Console.printLine("running at http://localhost:8080/text") *> 
    Server.serve(app).provide(Server.default)
}