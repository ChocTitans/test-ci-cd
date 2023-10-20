pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: maven
            image: maven:3.6.3-openjdk-17-slim
            command:
            - cat
            tty: true
          - name: docker
            image: docker:19.03.1-dind
            securityContext:
              privileged: true
            env:
              - name: DOCKER_TLS_CERTDIR
                value: "" 
        '''
    }
  }
  stages {
    stage('Clone') {
      steps {
        container('maven') {
              git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }
      }
    }  
    stage('Build-Jar-file') {
      steps {
        container('maven') {
          sh 'mvn clean install'
        }
      }
    }
    stage('Build-Docker-image') {
      steps {
        container('docker') {
          sh 'docker build -t eltitans/test-ci-cd .'
          script {
            withDockerRegistry(credentialsId: 'DockerHamza', url "") {
              sh 'docker push eltitans/test-ci-cd:latest'
            }
          }
        }
      }
    }
}
}


