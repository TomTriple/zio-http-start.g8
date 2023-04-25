package $package$

import zio._
import zio.http._

object MainApp extends ZIOAppDefault {

  val app: App[Any] = 
    Http.collect[Request] {
      case Method.GET -> !! / "text" => Response.text("Hello World!")
    }

  override val run =
    Console.printLine("running at port 8080") *> 
    Server.serve(app).provide(Server.default)
}