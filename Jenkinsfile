podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:19.03.1-dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')])
  ]) {

    node(POD_LABEL) {
        stage('Get a Maven project') {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
            container('maven') {
                stage('Build a Maven project') {
                    sh 'mvn -version'
                }
            }
        }

        stage('Get a Golang project') {
            container('docker') {
                stage('Build Docker project') {
                    sh 'docker build -t eltitans/test-ci-cd .'

                }
            }
        }

    }
}