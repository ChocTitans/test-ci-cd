podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:19.03.1-dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')])
  ]) {

    node(POD_LABEL)
    {
        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }

        stage('Maven install')
        {
            container('maven')
            {
                sh 'mvn clean install'
            }
        }

        stage('Docker build & push')
        {
            container('docker')
            {
                sh 'docker build -t eltitans/test-ci-cd .'
                script
                {
                    /*withDockerRegistry(credentialsId: 'DockerHamza', url: '')
                    {
                        sh 'docker push eltitans/test-ci-cd:latest'
                    }*/
                }
            }
        }
    }
}