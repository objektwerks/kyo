name := "kyo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.1-RC1"
libraryDependencies ++= {
  val kyoVersion = "0.8.5"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.2",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
