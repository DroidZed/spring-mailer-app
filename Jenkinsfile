pipeline {
    agent any
    environment {
        SMTP_HOST                       = credentials('SMTP_HOST')
        SMTP_PORT                       = credentials('SMTP_PORT')
        SMTP_USRNAME                    = credentials('SMTP_USRNAME')
        SMTP_PASSWORD                   = credentials('SMTP_PASSWORD')
        TO                              = credentials('TO')
        SERVER_PORT                     = credentials('SERVER_PORT')
        IMAGE_TAG_SPRING_MAILER_APP     = credentials('IMAGE_TAG_SPRING_MAILER_APP')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Validating...'
                sh "mvn validate"
                echo 'Compiling...'
                sh "mvn compile"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh "mvn test"
            }
        }
        stage('Docker Image') {
            steps {
                echo 'Building the docker image!'
            }
        }
    }
}