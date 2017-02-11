package sbtunidoc

import sbt._
import sbt.Keys._

object GenJavadocPlugin extends AutoPlugin {
  object autoImport extends GenJavaUnidocKeys {
    lazy val Genjavadoc = config("genjavadoc") extend Compile
  }
  import autoImport._

  override def globalSettings = unidocGenjavadocVersion := "0.10"

  override def projectSettings = Seq(
    libraryDependencies += compilerPlugin("com.typesafe.genjavadoc" %% "genjavadoc-plugin" % unidocGenjavadocVersion.value cross CrossVersion.full),
    scalacOptions <+= target map (t => "-P:genjavadoc:out=" + (t / "java")))
}
