name          := "sample-code"

organization  := "com.example"

version       := "0.2"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "sbt plugins snapshots" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases",
  "sbt plugins releases" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"   %%  "akka-actor"          %  "2.1.4",
  "com.typesafe.akka"   %%  "akka-testkit"        %  "2.1.4",
  "io.spray"            %   "spray-can"           %  "1.2-RC1",
  "io.spray"            %%  "spray-json"          %  "1.2.5",
  "io.spray"            %   "spray-routing"       %  "1.2-RC1",
  "io.spray"            %   "spray-testkit"       %  "1.2-RC1",
  "joda-time"           %   "joda-time"           %  "2.3",
  "org.apache.commons"  %   "commons-lang3"       %  "3.1",
  "org.joda"            %   "joda-convert"        %  "1.5",
  "org.hsqldb"          %   "hsqldb"              %  "2.3.1",
  "org.scalatest"       %%  "scalatest"           %  "2.0"      %  "test",
  "org.scalacheck"   	%%  "scalacheck"          %  "1.10.1"   %  "test"
)

