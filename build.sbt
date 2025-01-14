name := "kyo"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.6.3-RC2"
libraryDependencies ++= {
  val kyoVersion = "0.16.0"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.16",
    "org.scalameta" %% "munit" % "1.0.3" % Test
  )
}
parallelExecution := false
scalacOptions ++= Seq(
  "-Wall"
)
