package fm.dmm.superphone.sbtplugins

import sbt.{Def, _}
import sbt.Keys._


object SbtStdScalaConfigurer extends AutoPlugin with Common {
  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins =
    plugins.JvmPlugin && org.scalafmt.sbt.ScalafmtPlugin && wartremover.WartRemover

  object autoImport {
    lazy val stdScalafmtSetup: TaskKey[Unit] =
      taskKey[Unit]("Creates standardized scalafmt configuration")

    //lazy val publishSettings = settingKey[Seq[Setting[_]]("Settings to publish artifact")
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
  ) ++ noPublishSettings ++ isolatedSequentalTestsSettings


  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    resolvers ++= Seq(superphoneReleasesSonatype, superphoneSnapshotsSonatype)
  )

  override def projectSettings: Seq[Setting[_]] =
    defaultSettings
}

trait Common {
  // -- some common settings
  val superphoneReleasesSonatype = "Superphone Sonatype Releases" at "http://ec2-54-211-237-71.compute-1.amazonaws.com:8081/repository/maven-releases/"
  val superphoneSnapshotsSonatype = "Superphone Sonatype Snapshots" at "http://ec2-54-211-237-71.compute-1.amazonaws.com:8081/repository/maven-snapshots/"

  // disables publishing
  val noPublishSettings = Seq(
    publish := (),
    publishLocal := (),
    publishArtifact := false
  )

  val publishSettings = Seq(
    publishTo := Some {
      if (isSnapshot.value) superphoneSnapshotsSonatype
      else superphoneReleasesSonatype
    }
  )

  // makes sure test run in forked JVM, and not in parallel
  val isolatedSequentalTestsSettings = Seq(
    parallelExecution in Test := false,
    fork in run := true
  )
}
