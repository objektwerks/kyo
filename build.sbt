name := "kyo"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.6.3-RC1"
libraryDependencies ++= {
  val kyoVersion = "0.15.1"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.15",
    "org.scalameta" %% "munit" % "1.0.3" % Test
  )
}
parallelExecution := false
scalacOptions ++= Seq(
  "-Wall"
)
