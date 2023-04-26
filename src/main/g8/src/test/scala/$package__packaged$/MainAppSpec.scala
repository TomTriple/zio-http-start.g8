package $package$

import zio.test._
import zio.http._

object MainAppSpec extends ZIOSpecDefault {

  def spec = suite("suite for MainApp")(
    test("test for endpoint /text") {
      val request = Request.get(URL(Path.decode("/text")))
      MainApp.app.runZIO(request) *> assertTrue(true)
    }
  )

}
