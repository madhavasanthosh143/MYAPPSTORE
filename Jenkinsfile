pipeline {
    agent any
    
    tools {
        maven 'maven3'
    }
    
    environment {
        DOCKER_IMAGE = 'mmadhavasanthosh/myappstore'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Clone Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/madhavasanthosh143/MYAPPSTORE.git'
            }
        }
        
        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'PASSWORD'
                )]) {
                    sh "docker login -u ${USERNAME} -p ${PASSWORD}"
                    sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sh "docker rm -f myappstore || true"
                sh "docker run -d -p 9019:9019 --name myappstore ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
    }
}
