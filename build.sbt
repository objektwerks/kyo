name := "kyo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.2-RC1"
libraryDependencies ++= {
  val kyoVersion = "0.7.9"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.4.14",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)