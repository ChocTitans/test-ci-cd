podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:19.03.1-dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')])
  ]) {

    node(POD_LABEL)
    {
        stage('Install Docker Compose')
        {
            container('docker')
            {
                sh 'wget https://github.com/docker/compose/releases/download/1.29.2/docker-compose-Linux-x86_64 -O /usr/local/bin/docker-compose'
                sh 'chmod +x /usr/local/bin/docker-compose'  
            }
        }
        
        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }

        stage('Docker build & push')
        {
            container('docker')
            {
                sh 'docker-compose build '
                script
                {
                    withDockerRegistry(credentialsId: 'DockerHamza', url: '')
                    {
                        sh 'docker-compose push'
                    }
                }
            }
        }
    }
}