pipeline {
    agent any

    stages {
        stage("Hello") {
            steps {
                echo("Hello Pipeline")
            }
        }
    }

    post {
        always {
            echo "I will always say hello"
        }
        success {
            echo "Yay, success"
        }
        failure {
            echo "Oh no, failure"
        }
        cleanup {
            echo "don't care success or error"
        }
    }
}