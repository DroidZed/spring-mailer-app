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
                echo 'Cleaning...'
                sh "mvn clean"
            }
        }
        stage('Build') {
            steps {
                echo 'Validating...'
                sh "mvn validate"
                echo 'Compiling...'
                sh "mvn compile"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh "mvn test"
            }
        }
        stage('Code Coverage') {
            steps {
                echo 'Generating code coverage files..'
                sh "mvn jacoco:report"
            }
        }
        stage('Docker Image - Building') {
            steps {
                echo 'Building the docker image...'
                // sh "docker build -t droidzed/spring-mailer-app:$IMAGE_TAG_SPRING_MAILER_APP ."
                script { 
                    dockerImage = docker.build registry + ":$IMAGE_TAG_SPRING_MAILER_APP" 
                }
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    discordSend description: "Jenkins Pipeline Build: Docker image build failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                    sh "exit 1"
                }
            }
        }
        stage('Docker Image - Pushing To Registry') {
            steps {
                echo 'Pushing the docker image to docker hub...'
                // sh "docker login -u droidzed -p $DOCKER_PAT"
                // sh "docker push droidzed/spring-mailer-app:$IMAGE_TAG_SPRING_MAILER_APP"
                script {
                     docker.withRegistry('', dockerCredentials) { 
                        dockerImage.push() 
                     }
                }
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    discordSend description: "Jenkins Pipeline Build: Push to Docker hub failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                    sh "exit 1"
                }
            }
        }
        stage("Octopus") {
            steps {
                echo 'Running in compose!'
                sh "docker compose start -d"
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    discordSend description: "Jenkins Pipeline Build: Octopus failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                    sh "exit 1"
                }
            }
        }
        stage('SONAR') {
            steps {
                echo 'SonarQube running...'
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin"
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    discordSend description: "Jenkins Pipeline Build: Sonar failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                    sh "exit 1"
                }
            }
        }
        stage('Nexus') {
            steps {
                echo 'Deploying to nexus...'
                sh "mvn deploy -DskipTests"
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    discordSend description: "Jenkins Pipeline Build: Nexus failed!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                    sh "exit 1"
                }
            }
        }
        stage("Discord Notify") {
            steps {
                script {
                    discordSend description: "Pipeline ran successfully!", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                }
            }
        }
    }
}