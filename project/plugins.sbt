addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8"   % "0.16.1")
addSbtPlugin("org.scalameta"             % "sbt-scalafmt" % "2.5.0")

libraryDependencies ++=
  Seq(
    "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value,
  )
