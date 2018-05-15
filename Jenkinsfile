node {
    checkout scm

    def server = Artifactory.newServer url: 'http://agora.dequecloud.com/artifactory/attest-java/', credentialsId: '8e896f0b-2c0d-4acb-a297-82c959a7563e'

    stage('Build') {
          echo 'This is a minimal pipeline.'
          sh "mvn -X clean compile -DskipTests"

    }

}