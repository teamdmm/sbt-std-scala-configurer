package io.superphone.sbtplugins

import au.com.onegeek.sbtdotenv.SbtDotenv
import sbt.{Def, _}
import sbt.Keys._


object SbtStdScalaConfigurer extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins =
      plugins.JvmPlugin &&
      org.scalafmt.sbt.ScalafmtPlugin &&
      wartremover.WartRemover &&
      au.com.onegeek.sbtdotenv.SbtDotenv

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
  ) ++ CommonStatic.noPublishSettings ++ CommonStatic.commonSettings


  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    resolvers ++= Seq(CommonStatic.superphoneReleasesSonatype, CommonStatic.superphoneSnapshotsSonatype)
  )

  override def projectSettings: Seq[Setting[_]] =
    defaultSettings
}

object CommonStatic {
  val commonSettings = Seq(
    organization := "io.superphone",
    organizationName := "Disruptive Multimedia",
    testOptions in Test += Tests.Setup(() => SbtDotenv.trigger),
    sources in (Compile, doc) := Seq.empty,
    updateOptions := updateOptions.value.withCachedResolution(true),
    conflictManager := ConflictManager.strict
  )

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
