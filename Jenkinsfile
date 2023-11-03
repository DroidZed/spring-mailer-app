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
        dockerCredentials               = 'DOCKER_CREDS'
        registry                        = 'droidzed/spring-mailer-app'
        dockerImage                     = ''
        DISCORD_WEBHOOK_URL             = credentials("DISCORD_WEBHOOK_URL")
        JOB_NAME                        = "Spring-Mailer-App"
    }

    stages {
        stage('Clean') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Cleaning...'
                    sh "mvn clean"
                }
            }
        }
        stage('Build') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Validating...'
                    sh "mvn validate"
                    echo 'Compiling...'
                    sh "mvn compile"
                }
            }
        }
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Testing..'
                    sh "mvn test"
                }
            }
        }
        stage('Code Coverage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Generating code coverage files..'
                    sh "mvn jacoco:report"
                }
            }
        }
        stage('Docker Image - Building') {
            steps {
                echo 'Building the docker image...'
                script { 
                    dockerImage = docker.build registry + ":$IMAGE_TAG_SPRING_MAILER_APP" 
                }
            }
        }
        stage('Docker Image - Pushing To Registry') {
            steps {
                echo 'Pushing the docker image to docker hub...'
                script {
                    docker.withRegistry('', dockerCredentials) { dockerImage.push() }
                }
            }
        }
        stage("Octopus") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Running in compose!'
                    sh "docker compose start"
                    discordSend description: "Jenkins Pipeline Build: Octopus failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                }
            }
        }
        stage('SONAR') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'SonarQube running...'
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin"
                }
            }
        }
        stage('Nexus') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Deploying to nexus...'
                    sh "mvn deploy -DskipTests"
                }
            }
        }
        stage("Discord Notify") {
            steps {
                discordSend description: "Pipeline ran successfully!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
            }
        }
    }
}