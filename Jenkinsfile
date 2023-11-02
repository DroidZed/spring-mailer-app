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
        SONAR_TOKEN                     = credentials('SONAR_TOKEN')
        DOCKER_PAT                      = credentials('DOCKER_PAT')
        dockerUsername                  = 'droidzed'
        registry                        = 'droidzed/spring-mailer-app'
        dockerImage                     = "droidzed/spring-mailer-app:$IMAGE_TAG_SPRING_MAILER_APP"
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
            }
        }
        stage('Docker Image - Pushing To Registry') {
            steps {
                echo 'Pushing the docker image to docker hub...'
                // sh "docker login -u $dockerUsername -p $DOCKER_PAT"
                // sh "docker push droidzed/spring-mailer-app:$IMAGE_TAG_SPRING_MAILER_APP"
                script {
                     docker.withRegistry( '', dockerUsername) { 
                        dockerImage.push() 
                     }
                }
            }
        }
        stage("Octopus") {
            steps {
			    echo 'Running in compose!'
                sh "docker-compose up -d"
            }
        }
        stage('SONAR') {
            steps {
                echo 'SonarQube running...'
                sh "mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN"
            }
        }
        stage('Nexus') {
            steps {
                echo 'Deploying to nexus...'
                sh "mvn deploy -DskipTests"
            }
        }
        stage("Discord Notify") {
            steps {
                script {
                    discordSend description: "Jenkins Pipeline Build", footer: "Ran From Localhost", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: ":$DISCORD_WEBHOOK_URL"
                }
            }
        }
    }
}