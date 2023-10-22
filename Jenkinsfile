pipeline {
    agent{
        kubernetes{
            
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