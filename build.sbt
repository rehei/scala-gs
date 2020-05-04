name := "scala-gs"

organization := "com.github.rehei"

version := sys.props.getOrElse("tag", default = "0.0.0")

scalaVersion := "2.12.3"
crossScalaVersions := Seq("2.12.4", "2.11.11")

libraryDependencies ++= {
  Seq(
    "org.graphstream" % "gs-ui" % "1.3"
  )
}

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

EclipseKeys.withSource := true
