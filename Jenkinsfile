pipeline {
    agent any
	 tools {
        maven 'maven 3.9.9'  // <-- use your configured Maven name here
    }
     stages {
        stage('Debug environment') {
            steps {
                sh 'echo "JAVA_HOME=$JAVA_HOME"'
                sh 'java -version'
                sh 'mvn --version'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
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

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image inside Minikube Docker') {
            steps {
                script {
                    // Use Minikube's Docker daemon
                    sh 'eval $(minikube docker-env) && docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
                }
            }
        }

        stage('Deploy to Minikube (Kubernetes)') {
            steps {
                script {
                    // Apply Kubernetes manifests
                    sh 'kubectl apply -f k8s/deployment.yaml'
                    sh 'kubectl apply -f k8s/service.yaml'
                }
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
