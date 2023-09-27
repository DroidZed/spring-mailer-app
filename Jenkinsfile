pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Validating...'
                dir('spring-mailer-app') {
                    sh "make validate"
                }
                echo 'Compiling...'
                dir('spring-mailer-app') {
                    sh "make compile"
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                dir('spring-mailer-app') {
                    sh "make test"
                }
            }
        }
        stage('Docker Image') {
            steps {
                echo 'Building the docker image!'
            }
        }
    }
}