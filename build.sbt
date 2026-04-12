name := "kyo"
organization := "objektwerks"
version := "1.0.0"
scalaVersion := "3.8.3"
libraryDependencies ++= {
  val kyoVersion = "1.0-RC1"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.32",
    "org.scalameta" %% "munit" % "1.2.2" % Test
  )
}
parallelExecution := false
scalacOptions ++= Seq(
  "-Wunused:all"
)
