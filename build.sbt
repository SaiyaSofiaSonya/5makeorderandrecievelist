import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "ru.test",
      scalaVersion := "2.12.12",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "testprojecttask3",
    libraryDependencies ++= gatling
  )

scalacOptions ++= Seq(
  "-language:existentials",
  "-language:implicitConversions",
)

enablePlugins(GatlingPlugin)