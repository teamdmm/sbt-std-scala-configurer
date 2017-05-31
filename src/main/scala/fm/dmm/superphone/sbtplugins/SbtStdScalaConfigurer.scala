package fm.dmm.superphone.sbtplugins

import sbt._
import sbt.Keys._

object SbtStdScalaConfigurer extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins =
    plugins.JvmPlugin && org.scalafmt.sbt.ScalafmtPlugin && wartremover.WartRemover

  object autoImport {
    lazy val stdScalafmtSetup: TaskKey[Unit] =
      taskKey[Unit]("Creates standardized scalafmt configuration")
  }

  import autoImport._
  lazy val defaultSettings: Seq[Setting[_]] = Seq(
    // -- Configure Scalac:
    scalacOptions ++= ScalaFlags.flagsFor(scalaVersion.value),

    // -- Configure ScalaFmt:
    stdScalafmtSetup := ScalaFmtConfigurer.writeConf.value,

    // Before compiling setup a scalafmt. There may be better task for this
    (compile in Compile) := (compile in Compile)
      .dependsOn(stdScalafmtSetup)
      .value,

    // -- Configure WartRemover:
    wartremover.wartremoverExcluded := WartRemoverConfig.ignoreRoutes(crossTarget.value),

    // do not warn for tests (more relaxed environment)
    wartremover.wartremoverWarnings in (Compile, compile) ++= WartRemoverConfig.flaggedWarts
  )

  override def projectSettings: Seq[Setting[_]] =
    defaultSettings
}
