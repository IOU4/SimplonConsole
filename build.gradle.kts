plugins { application }

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("junit:junit:4.13.2")

    implementation("org.apache.commons:commons-math:2.2")
    implementation("org.json:json:20220924")
}

tasks {
  named<JavaExec>("run") {
    standardInput = System.`in`
  }
}
application {
  mainClass.set("simplonclone.App")
}
