pipeline {
    agent any

    stages {
        stage("Hello") {
            steps {
                echo("Hello Pipeline")
            }
        }

        stage("Build"){
            steps {
                echo("Hello Build")
            }
        }

        stage("Test"){
            steps {
                sh("error")
            }
        }

        stage("Deploy"){
            steps {
                echo("Hello Deploy")
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