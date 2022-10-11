plugins { application }

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.postgresql:postgresql:42.5.0")
}

tasks {
  named<JavaExec>("run") {
    standardInput = System.`in`
  }
}
application {
  mainClass.set("simplonclone.Main")
}
