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
              volumeMounts:
              - name: dockersock
                mountPath: /var/run/docker.sock
            - name : maven
              image: maven:3.6.3-openjdk-17-slim
              command:
              - cat
              tty: true
            volumes:
            - name: dockersock
              hostPath:
                path: /var/run/docker.sock            
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
