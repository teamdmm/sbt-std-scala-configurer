name := "sbt-std-scala-configurer"

// some duplication between this and actual implementation (settings) we provide,
// since it's cyclical dependency

organization in ThisBuild := "io.superphone"
organizationName in ThisBuild := "Disruptive Multimedia"

// because until sbt is 1.x we have to stick to old scala
scalaVersion := "2.10.6"

sbtPlugin := true

addSbtPlugin(Dependencies.wartRemover)
addSbtPlugin(Dependencies.scalaFmt)

licenses +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))

val superphoneReleasesSonatype = "Superphone Sonatype Releases" at "http://ec2-54-211-237-71.compute-1.amazonaws.com:8081/repository/maven-releases/"
val superphoneSnapshotsSonatype = "Superphone Sonatype Snapshots" at "http://ec2-54-211-237-71.compute-1.amazonaws.com:8081/repository/maven-snapshots/"

publishTo := Some {
  if (isSnapshot.value) superphoneSnapshotsSonatype
  else superphoneReleasesSonatype
}
