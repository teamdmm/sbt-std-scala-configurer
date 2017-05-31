name := "sbt-std-scala-configurer"

organization := "fm.dmm.superphone"

scalaVersion := "2.10.6"

sbtPlugin := true

addSbtPlugin(Dependencies.wartRemover)
addSbtPlugin(Dependencies.scalaFmt)

licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))
