package $package$

import zio._
import zio.http._
import zio.http.endpoint._

object MainApp extends ZIOAppDefault {


  // Web Assets

  def makeWebAssets:App[Any] = {
    Http.collectHandler[Request] {
      case Method.GET -> "" /: "assets" /: path =>
        Http.fromFile(
          new java.io.File(s"./src/main/resources/\${path.encode}")
        ).toHandler(Handler.notFound)
    }.withDefaultErrorResponse    
  }


  // Web Application

  def makeWebApp:App[Any] = {
    import zio.http.html._    
    def withContentHtml(content: Html) =
      html(
        head(
          title("ZIO Http"), 
          link(relAttr := "stylesheet", href := "/assets/styles.css")
        ), 
        body(
          div(
            id := "starter", 
            ul(
              li(a(href := "/", img(srcAttr := "/assets/zio.png"))),
              li(h2("You're running on ZIO Http!")),
              li(a(href := "https://zio.dev/zio-http/", "Documentation")), 
              li(a(href := "https://www.github.com/zio/zio-http/", "Github")), 
              li(b("Show WebAPI Example")),
              li(a(href := "/api/users/99?show-details=true", "WebAPI Example")), 
              li(b("Build a local docker image")), 
              li(pre("sbt:$name$> docker:publishLocal")), 
              li(b("Run container")), 
              li(pre("\$ docker run -p 8080:8080 $name;format="norm"$:$version$"))
            )
          ), 
          content
        )
      )

    Http.collect[Request] {
      case Method.GET -> !! => 
        import zio.http.html._
        Response.html(withContentHtml(div()))
    }
  }


  // Web API
  
  def makeWebAPI:App[Any] = {
    import zio.http.codec.HttpCodec._
    Endpoint
      .get("api" / "users" / int("userId"))
      .query(queryBool("show-details"))
      .out[String]
      .implement { 
        case (userId, showDetails) => 
          ZIO.succeed("user " + userId + " with details? " + showDetails)
      }.toApp    

  }

  val routes = makeWebApp ++ makeWebAPI ++ makeWebAssets

  override val run =
    Console.printLine("please visit http://localhost:8080") *> 
    Server.serve(routes).provide(Server.default)
}
