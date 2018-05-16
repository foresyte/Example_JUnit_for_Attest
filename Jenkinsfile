node {
    checkout scm


    def server = Artifactory.newServer url: 'http://agora.dequecloud.com/artifactory/attest-java/', username: 'rich.szymczak@deque.com', password: 'Thund3rcl4p!'

    stage('Build') {
          echo 'This is a minimal pipeline.'
          sh "mvn -X clean compile -DskipTests"

    }

}