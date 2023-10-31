podTemplate(containers: [
    //containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')]),
  ]) {

    node(POD_LABEL)
    {
        stage ('Installing Requirements')
        {
            container('docker')
            {
                script
                {
                    sh 'dockerd-entrypoint.sh &'
                    sh 'until docker info; do sleep 1; done'
                    sh 'apk add kustomize'
                }
            }
        }
        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }

       /* stage('Docker build & push')
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
        }*/

        stage('SonarQube Test Vulnerabilty')
        {
            dir('vote') 
            {
                withSonarQubeEnv('My SonarQube Server') 
                {
                    sh "sonar-scanner"
                }
            }
            dir('result')
            {
                withSonarQubeEnv('My SonarQube Server')
                {
                    sh "sonar-scanner-cli" 
                }
            }
            dir('worker')
            {
                withSonarQubeEnv('My SonarQube Server')
                {
                    sh "sonar-scanner begin"
                    sh "dotnet build worker.csproj"
                    sh "sonar-scanner end"
                }
            }
            
        }
        stage('Deploy to K8s')
        {
           /* kubeconfig(credentialsId: 'Github-Hamza', serverUrl: '')
            {
                kubectl cluster-info
            }*/
        }
    }
}