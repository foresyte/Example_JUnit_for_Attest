node {
    checkout scm

    stage('Build') {
          echo 'This is a minimal pipeline.'
          sh "${mvnHome}/bin/mvn clean install -DskipTests"

    }

}