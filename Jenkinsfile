pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mohammeddl/insurance-app'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS = 'dockerhub-credentials' 
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning Git Repository...'
                checkout scm 
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building Maven Project...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image...'
                sh """
                docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                """
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Pushing Docker Image to Docker Hub...'
                withDockerRegistry([credentialsId: "${DOCKER_CREDENTIALS}", url: '']) {
                    sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
