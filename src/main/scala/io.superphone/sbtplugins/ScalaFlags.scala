package fm.dmm.superphone.sbtplugins

import sbt._
import sbt.Keys._

object ScalaFlags {

  def flagsFor(scalaVersion: String) =
    if (scalaVersion.startsWith("2.11")) Scala2_11
    else if (scalaVersion.startsWith("2.12")) Scala2_12
    else throw new Exception("Scala version is not supported by sbt plugin")

  //https://tpolecat.github.io/2014/04/11/scalac-flags.html
  private val Scala2_11 =
    Seq(
      "-deprecation",
      "-encoding",
      "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-unchecked",
      //"-Xfatal-warnings",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code", // N.B. doesn't work well with the ??? hole
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Xfuture"
      //"-Ywarn-unused-import" // 2.11 only, broken with Play routes
    )

  //https://tpolecat.github.io/2017/04/25/scalac-flags.html
  private val Scala2_12 =
    Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8", // Specify character encoding used by source files.
      "-explaintypes", // Explain type errors in more detail.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros", // Allow macro definition (besides implementation and application)
      "-language:higherKinds", // Allow higher-kinded types
      "-language:implicitConversions", // Allow definition of implicit functions called views
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
      //"-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
      "-Xfuture", // Turn on future language features.
      "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
      "-Xlint:by-name-right-associative", // By-name parameter of right associative operator.
      "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
      "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
      "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
      "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
      "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
      "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
      "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
      "-Xlint:option-implicit", // Option.apply used implicit view.
      "-Xlint:package-object-classes", // Class or object defined in package object.
      "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
      "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
      "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
      "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
      "-Xlint:unsound-match", // Pattern match may not be typesafe.
      "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
      "-Ypartial-unification", // Enable partial unification in type constructor inference
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
      "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
      "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
      "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
      "-Ywarn-numeric-widen", // Warn when numerics are widened.
      "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
      "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
      "-Ywarn-unused:locals", // Warn if a local definition is unused.
      "-Ywarn-unused:params", // Warn if a value parameter is unused.
      "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
      "-Ywarn-unused:privates", // Warn if a private member is unused.
      "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
    )

}
