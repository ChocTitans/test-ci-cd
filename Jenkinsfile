pipeline {
    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
spec:
containers:
- name: docker-client
  image: docker:latest
  command: ['sleep', '99d']
  env:
    - name: DOCKER_HOST
      value: tcp://localhost:2375
  volumeMounts:
    - name: cache
      mountPath: /tmp/repository
- name: docker-daemon
  image: docker:19.03.1-dind
  env:
    - name: DOCKER_TLS_CERTDIR
      value: ""
  securityContext:
    privileged: true
  volumeMounts:
    - name: cache
      mountPath: /var/lib/docker
volumes:
  - name: cache
    hostPath:
      path: /tmp
      type: Directory
'''
        }
    }
    stages {
        stage('Checkout') {
            steps {
              git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
            }
        }
        stage('Build') {
            steps {
                container('docker-client') {
                    sh "docker run -v ${WORKSPACE}:/usr/src/mymaven -v /tmp/repository:/root/.m2/repository -w /usr/src/mymaven maven:3.6.3-jdk-17-slim mvn clean install"
                }
            }
        }
    }
}



