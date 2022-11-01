plugins { application }

repositories { mavenCentral() }
// define jdk version
java { sourceCompatibility = JavaVersion.VERSION_19 }

dependencies { implementation("org.postgresql:postgresql:42.5.0") }

tasks { named<JavaExec>("run") { standardInput = System.`in` } }

application { mainClass.set("simplonclone.Main") }
