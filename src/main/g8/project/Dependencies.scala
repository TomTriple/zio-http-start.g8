import sbt._

object Dependencies {

  val zioVersion = "$zioVersion$"
  val zioHttpVersion = "$zioHttpVersion$"

  val zioHttp     = "dev.zio" %% "zio-http"     % zioHttpVersion
  
  val zioTest     = "dev.zio" %% "zio-test"     % zioVersion % Test
  val zioTestSBT = "dev.zio" %% "zio-test-sbt" % zioVersion % Test
  val zioTestMagnolia = "dev.zio" %% "zio-test-magnolia" % zioVersion % Test  
}
