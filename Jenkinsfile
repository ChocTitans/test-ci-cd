podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')]),
  ]) {

    node(POD_LABEL)
    {
        stage ('Installing Requirements')
        {
            /*container('docker')
            {
                script
                {
                    sh 'dockerd-entrypoint.sh &'
                    sh 'until docker info; do sleep 1; done'
                    sh 'apk add kustomize'
                }
            }*/
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
        stage('Verify SonarQube Connection') {
            steps {
                script {
                    def sonarqubeURL = 'https://sonarqube.hamzaboubnane.tech'  // Replace with the actual URL of your SonarQube server
                    def responseCode = null
        
                    // Make an HTTP request to the SonarQube URL and capture the response code
                    withSonarQubeEnv('sonarqube') {
                        responseCode = sh(script: "curl -s -o /dev/null -w '%{http_code}' $sonarqubeURL", returnStatus: true)
                    }
        
                    if (responseCode == 200) {
                        echo "Successfully reached SonarQube server (HTTP status code 200)."
                    } else {
                        error "Failed to reach SonarQube server. HTTP status code: $responseCode"
                    }
                }
            }
        }

        stage('SonarQube Test Vulnerabilty')
        {
            dir('vote') 
            {
                withSonarQubeEnv('sonarqube')
                {
                    sh "sonar-scanner"
                }
            }
            dir('result')
            {
                withSonarQubeEnv(installationName: 'sonarqube')
                {
                    sh "sonar-scanner-cli" 
                }
            }
            dir('worker')
            {
                withSonarQubeEnv(installationName: 'sonarqube')
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
