plugins { application }

repositories {
  mavenCentral()
}

dependencies {
 
  implementation("org.postgresql:postgresql:42.5.0")
  implementation("org.json:json:20220924")
}

tasks {
  named<JavaExec>("run") {
    standardInput = System.`in`
  }
}
application {
  mainClass.set("simplonclone.Main")
}
