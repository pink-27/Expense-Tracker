pipeline
{
    environment{
        BACKEND_IMAGE_NAME = "pink27/backend"
        FRONTEND_IMAGE_NAME = "pink27/frontend"
        POSTGRESQL_IMAGE="postgres:latest"
        POSTGRES_USER="myappdb"
        POSTGRES_PSSWD="abc123"
        registryCredential = "dockersignin"
        backendImage=""
        frontendImage=""
    }
    agent any
    stages
    {
        stage('Stage 0.0: DB docker image'){
            steps{
                echo "Pulling DB docker image"
                script{
                    docker.withRegistry('',registryCredential){
                        docker.image("${POSTGRESQL_IMAGE}").pull()
                    }
                }
            }
        }
        stage('Stage 0.1: Start DB container'){
            steps{
                script{
                    sh 'docker run --name postgresql -d -p 5432:5432 -e POSTGRES_USER=myappdb -e POSTGRES_PASSWORD=abc123 -e POSTGRES_DB=minisplitwise postgres:latest'

                }
            }
        }
        stage('Stage 1: Git Clone')
        {
            steps
            {
                git branch: 'master',
                url:'https://github.com/Tejsharma15/SPE-MajorProject-Splitwise'
            }
        }
        stage('Stage 2: Compile Frontend') {
            steps {
                dir('frontend'){
                    sh 'npm install && npm run build'
                }
            }
        }
        stage('Stage 3: Compile Backend') {
            steps {
                dir('MiniSplitwise'){
                    sh 'mvn clean install'
                }
            }
        }
        stage('Stage 4: Build and Push Frontend Docker Image') {
            steps {
                script {
                    frontendImage = docker.build(env.FRONTEND_IMAGE_NAME,'./frontend')
                    docker.withRegistry('', registryCredential) {
                        frontendImage.push('latest')
                    }
                }
            }
        }
        stage('Stage 5: Build and Push Backend Docker Image') {
            steps {
                script {
                    backendImage = docker.build(env.BACKEND_IMAGE_NAME, './MiniSplitwise')
                    docker.withRegistry('', registryCredential) {
                        backendImage.push('latest')
                    }
                }
            }
        }
        stage('Stage 6: Clean Docker Images') {
            steps {
                script {
                    sh 'docker rmi $BACKEND_IMAGE_NAME'
                    sh 'docker rmi $FRONTEND_IMAGE_NAME'
                    sh 'docker container stop postgresql'
                    sh 'docker container rm postgresql'
                    sh 'docker rmi $POSTGRESQL_IMAGE'
                }
            }
        }
        stage('Stage 7: Ansible Deployment'){
            steps {
                ansiblePlaybook colorized: true,
                installation: 'Ansible',
                inventory: 'inventory',
                playbook: 'playbook.yml'
            }
        }
    }
}
