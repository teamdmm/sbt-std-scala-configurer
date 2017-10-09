// some duplication between this and actual implementation (settings) we provide,
// since it's cyclical dependency

organization in ThisBuild := "io.superphone"
organizationName in ThisBuild := "Disruptive Multimedia"
name := "sbt-std-scala-configurer"

// because until sbt is 1.x we have to stick to old scala
scalaVersion := "2.10.6"

sbtPlugin := true

addSbtPlugin(Dependencies.wartRemover)
addSbtPlugin(Dependencies.scalaFmt)
addSbtPlugin(Dependencies.dotEnv)
addSbtPlugin(Dependencies.coursier)

licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))

val superphoneReleasesSonatype = "Superphone Sonatype Releases" at "https://repo.superphone.io/repository/maven-releases/"
val superphoneSnapshotsSonatype = "Superphone Sonatype Snapshots" at "https://repo.superphone.io/repository/maven-snapshots/"

publishTo := Some {
  if (isSnapshot.value) superphoneSnapshotsSonatype
  else superphoneReleasesSonatype
}
