import Dependencies._

// give the user a nice default project!
ThisBuild / organization := "$organisation$"
ThisBuild / version := "$version$"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(BuildHelper.stdSettings)
  .settings(
    name := "$name$",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= Seq(zioHttp, zioTest, zioTestSBT, zioTestMagnolia),
  )
  .settings(
    Docker / version          := version.value,
    dockerBaseImage := "eclipse-temurin:20.0.1_9-jre",  
    Compile / run / mainClass := Option("$package$.MainApp"),
  )

addCommandAlias("fmt", "scalafmt; Test / scalafmt; sFix;")
addCommandAlias("fmtCheck", "scalafmtCheck; Test / scalafmtCheck; sFixCheck")
addCommandAlias("sFix", "scalafix OrganizeImports; Test / scalafix OrganizeImports")
addCommandAlias("sFixCheck", "scalafix --check OrganizeImports; Test / scalafix --check OrganizeImports")
