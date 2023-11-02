podTemplate(containers: [
    containerTemplate(name: 'maven', image: 'maven:3.6.3-openjdk-17-slim', command: 'cat', ttyEnabled: 'true'),
    containerTemplate(name: 'docker', image: 'docker:dind', command: '', ttyEnabled: true, privileged: true, envVars: [envVar(key: 'DOCKER_TLS_CERTDIR', value: '')]),
  ]) {

    node(POD_LABEL)
    {
        script {scannerHome = tool 'sonarqube' }

        environment {
            KUBE_NAMESPACE = 'devops-tools'
            AWS_CLI_INSTALL_DIR = "${WORKSPACE}/aws-cli" // Use WORKSPACE instead of env.WORKSPACE

        }
        stage ('Installing Requirements')
        {
            container('docker')
            {
                script
                {
                    def awsCliInstallDir = "${AWS_CLI_INSTALL_DIR}"

                    //sh 'dockerd-entrypoint.sh &'
                    //sh 'until docker info; do sleep 1; done'
                    sh "mkdir -p ${AWS_CLI_INSTALL_DIR}"
                    sh "wget -O ${AWS_CLI_INSTALL_DIR}/awscliv2.zip https://d1vvhvl2y92vvt.cloudfront.net/awscli-exe-linux-x86_64.zip"
                    sh "unzip ${AWS_CLI_INSTALL_DIR}/awscliv2.zip -d ${AWS_CLI_INSTALL_DIR}"
                    sh "${AWS_CLI_INSTALL_DIR}/aws/install -i ${AWS_CLI_INSTALL_DIR}"


                    sh 'apk add kustomize'
                    sh 'wget -O kubectl https://dl.k8s.io/release/$(wget -qO- https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl'
                    sh 'chmod +x kubectl'
                    sh 'mv kubectl /usr/local/bin/'
                }
            }
        }
        stage ('Clone')
        {
            git branch: 'main', changelog: false, credentialsId: 'Github-Hamza', poll: false, url: 'https://github.com/ChocTitans/test-ci-cd.git'
        }

        stage('Deploy to K8s')
        {
            container('docker')
            {
                dir('k8s')
                {
                    script {
                        kubeconfig(credentialsId: 'Kubeconfing', serverUrl: '') {
                            sh 'kubectl apply -f k8s/worker/deployment.yaml'
                        }
                    }
                }
            }
        }
    }
}
  
