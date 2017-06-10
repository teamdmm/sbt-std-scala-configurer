import sbt._

private object Dependencies {
  val scalaFmt = "com.geirsson" % "sbt-scalafmt" % "0.6.8"
  val wartRemover = "org.wartremover" % "sbt-wartremover" % "2.1.0"
  val dotEnv = "au.com.onegeek" %% "sbt-dotenv" % "1.1.36"
}