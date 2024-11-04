name := "kyo"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.6.1"
libraryDependencies ++= {
  val kyoVersion = "0.13.2"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.12",
    "org.scalameta" %% "munit" % "1.0.2" % Test
  )
}
parallelExecution := false
scalacOptions ++= Seq(
  "-Wall"
)
