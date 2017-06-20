import sbt._

private object Dependencies {
  val scalaFmt = "com.geirsson" % "sbt-scalafmt" % "0.6.8"
  val wartRemover = "org.wartremover" % "sbt-wartremover" % "2.1.1"
  val dotEnv = "au.com.onegeek" %% "sbt-dotenv" % "1.1.36"
  val coursier = "io.get-coursier" % "sbt-coursier" % "1.0.0-RC3"
}
