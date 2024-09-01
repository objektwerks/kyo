name := "kyo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.5.1-RC2"
libraryDependencies ++= {
  val kyoVersion = "0.11.1"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.7",
    "org.scalameta" %% "munit" % "1.0.1" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
