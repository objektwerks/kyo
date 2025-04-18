name := "kyo"
organization := "objektwerks"
version := "0.3-SNAPSHOT"
scalaVersion := "3.7.0-RC3"
libraryDependencies ++= {
  val kyoVersion = "0.18.0"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.18",
    "org.scalameta" %% "munit" % "1.1.0" % Test
  )
}
parallelExecution := false
scalacOptions ++= Seq(
  "-Wunused:all"
)
