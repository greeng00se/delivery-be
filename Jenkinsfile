pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh './gradlew clean build -x check --parallel'
      }
    }

    stage('test') {
      steps {
        sh './gradlew test'
      }
    }

    stage('zip') {
      steps {
        sh 'mv ./build/libs/delivery.jar .'
        sh 'zip -r delivery.zip .platform delivery.jar'
      }
    }

    stage('upload') {
      steps {
        sh 'aws s3 cp build/libs/delivery.zip s3://jenkins-deploy-myeats/delivery.zip --region ap-northeast-2'
      }
    }

    stage('deploy') {
      steps {
        sh 'aws elasticbeanstalk create-application-version --region ap-northeast-2 --application-name myeats --version-label ${BUILD_TAG} --source-bundle S3Bucket="jenkins-deploy-myeats",S3Key="delivery.zip"'
        sh 'aws elasticbeanstalk update-environment --region ap-northeast-2 --environment-name Myeats-env --version-label ${BUILD_TAG}'
      }
    }

  }
}
