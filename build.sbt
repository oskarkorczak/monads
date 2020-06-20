import Dependencies._

ThisBuild / organization := "com.korczak"
ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:_",
  "-unchecked",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)

lazy val `fp-library` =
  project
    .in(file("./1-fp-library"))

lazy val `application-library` =
  project
    .in(file("./2-application-library"))
    .dependsOn(`fp-library`)

lazy val `end-of-the-world` =
  project
    .in(file("./3-end-of-the-world"))
    .dependsOn(`application-library`)
