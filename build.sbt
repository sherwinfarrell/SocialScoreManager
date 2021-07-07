name := "UrlScoreManager"

version := "0.1"

scalaVersion := "2.12.13"


libraryDependencies ++=  Seq(
  "org.scalactic"  %% "scalactic"  % "3.0.4",
  "org.scalatest"  %% "scalatest"  % "3.0.4"  % Test,
  "org.scalamock"  %% "scalamock"  % "4.1.0"  % Test,
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test,
    "commons-validator" % "commons-validator" % "1.7",
  )

