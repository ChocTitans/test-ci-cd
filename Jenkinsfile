podTemplate(containers: [
    containerTemplate(name: 'docker', image: 'docker:dind', command: 'sh -c "dockerd-entrypoint.sh"', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')])
  ]) {

    node(POD_LABEL)
    {

        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }

        stage ('Installing docker compose')
        {
            container('docker')
            {
                sh 'apk add docker-compose'
            }
        }
        
        stage('Docker build & push')
        {

            container('docker')
            {
                script
                {
                    withDockerRegistry(credentialsId: 'DockerHamza', url: '')
                    {
                        sh 'docker-compose build'
                        sh 'docker-compose push'
                    }
                }
            }
        }
    }
}