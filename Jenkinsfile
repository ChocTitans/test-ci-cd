podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:dind', command: 'sh -c "dockerd-entrypoint.sh & until docker info; do sleep 1; done && apk add docker-compose"', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')], alwaysPullImage: true)
  ]) {

    node(POD_LABEL)
    {
        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
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