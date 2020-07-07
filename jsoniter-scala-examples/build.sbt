val `jsoniter-scala-examples` = project.in(file("."))
  .settings(
    scalaVersion := "2.13.3",
    scalacOptions ++= Seq("-Xmacro-settings:print-codecs"),
    crossScalaVersions := Seq("2.13.3", "2.12.11", "2.11.12"),
    mainClass in assembly := Some("com.github.plokhotnyuk.jsoniter_scala.examples.Example01"),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "1.0.0-RC21-2",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "latest.integration" % "runtime-internal",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "latest.integration" % "compile-internal" // or "provided", but it is required only in compile-time
    ) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 13)) => Seq("org.graalvm.nativeimage" % "svm" % "20.1.0" % "compile-internal") // or "provided", but it is required only in compile-time
      case _ => Seq()
    })
  )
