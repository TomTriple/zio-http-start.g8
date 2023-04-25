package $package$

import zio.test._

object $name;format="Camel"$Spec extends ZIOSpecDefault {
  override def spec: ZSpec[Environment, Failure] = suite("""$name;format="Camel"$Spec""")(
    testM("200 ok") {
      checkAllM(Gen.fromIterable(List("text", "json"))) { uri =>
        val request = Request(Method.GET, URL(!! / uri))
        assertM($name;format="Camel"$.app(request).map(_.status))(equalTo(Status.OK))
      }
    },
  )
}
