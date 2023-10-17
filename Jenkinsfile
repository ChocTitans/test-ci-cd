pipeline {
    agent
    {
      kubernetes {
        yaml '''
          apiVersion: v1
          kind: Pod
          spec:
            containers:
            - name: docker
              image: docker:latest
              command:
              - cat
              tty: true
            - name : maven
              image: maven:3.6.3-jdk-17
              command:
              - cat
              tty: true
          '''
      }
    }
    stages{
        stage('Git'){
            steps{
              git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
            }
        }
        stage('maven build') {
            steps {
              container('maven') {
                sh 'mvn clean install'
              }
            }
        }
        stage('Docker build and Push'){
            steps{
              container('docker'){
                sh 'docker build -t eltitans/test-ci-cd .'
                sh 'docker push eltitans/test-ci-cd:latest'
              }
            }

        }
    }
}
