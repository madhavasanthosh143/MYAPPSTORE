pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    environment {
        IMAGE_NAME = "myappstore"
        CONTAINER_NAME = "myapp"
        PORT = "9019"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/madhavasanthosh143/MYAPPSTORE.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                docker run -d -p $PORT:$PORT --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
    }
}
