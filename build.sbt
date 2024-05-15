name := "kyo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.2"
libraryDependencies ++= {
  val kyoVersion = "0.9.3"
  Seq(
    "io.getkyo" %% "kyo-core" % kyoVersion,
    "io.getkyo" %% "kyo-direct" % kyoVersion,
    "ch.qos.logback" % "logback-classic" % "1.5.6",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
