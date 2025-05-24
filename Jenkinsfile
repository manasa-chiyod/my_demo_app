pipeline {
    agent any

    tools {
        maven 'maven 3.9.9'  // your exact Maven tool name here
    }

    environment {
        IMAGE_NAME = "myapp"
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Clone from Git') {
            steps {
                git 'https://github.com/manasa-chiyod/my_demo_app.git'
            }
        }

        stage('Debug environment') {
            steps {
                bat 'echo "JAVA_HOME=$JAVA_HOME"'
                bat 'java -version'
                bat 'mvn --version'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

       stage('Build Docker Image inside Minikube Docker') {
    steps {
        bat '''
        powershell -Command "& { 
            $Env:DOCKER_TLS_VERIFY = '1'; 
            $Env:DOCKER_HOST = 'tcp://127.0.0.1:60846'; 
            $Env:DOCKER_CERT_PATH = 'C:\\Users\\User\\.minikube\\certs'; 
            $Env:MINIKUBE_ACTIVE_DOCKERD = 'minikube'; 
            docker build -t %IMAGE_NAME%:%IMAGE_TAG% . 
        }"
        '''
    }
}


        stage('Deploy to Minikube (Kubernetes)') {
            steps {
                bat 'kubectl apply -f k8s/deployment.yaml'
                bat 'kubectl apply -f k8s/service.yaml'
            }
        }
    }

    post {
        success {
            echo 'App successfully built and deployed to Minikube.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
