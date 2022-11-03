plugins { application }

repositories { mavenCentral() }
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_19 }

dependencies { 
  implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.eclipse.angus:jakarta.mail:1.0.0")
}

tasks { 
  named<JavaExec>("run") {
    standardInput = System.`in` 
    file(".env").readLines().forEach {
        environment(it.split("=")[0], it.split("=")[1])
      }
  } 
}

application { mainClass.set("simplonclone.Main") }
