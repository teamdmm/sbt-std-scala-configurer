package io.superphone.sbtplugins

import sbt._
import sbt.Keys._

object ScalaFmtConfigurer {
//#https://olafurpg.github.io/scalafmt/#rewrite.rules
 val scalaFmtConfig =
   """
   |# DO NOT MODIFY BY HAND, PART OF STD PLUGIN
   |
   |style = defaultWithAlign # For pretty alignment.
   |maxColumn = 110
   |
   |newlines.alwaysBeforeTopLevelStatements = false
   |
   |rewrite.rules = [AvoidInfix, RedundantBraces, RedundantParens, SortImports, PreferCurlyFors]
   """.stripMargin.trim

 def writeConf: Def.Initialize[Task[Unit]] = Def.task {
   val writeTo = baseDirectory.value / ".scalafmt.conf"
   IO.write(writeTo, scalaFmtConfig, IO.defaultCharset, append = false)
 }

}
