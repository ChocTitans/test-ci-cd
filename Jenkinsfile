pipeline {
    agent{
        kubernetes{
        yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name : git
            image: alpine/git
            command:
            - cat
            tty: true
          - name: maven
            image: maven:alpine
            command:
            - cat
            tty: true
        '''          
            
        }
    }

  stages {
    stage('Checkout SCMS')
    {
      steps {
        git branch: 'main', url: 'https://github.com/DevOps-Enthusiastic/Project-A', credentialsId: 'Github-Hamza'
      }
    }
  }
}