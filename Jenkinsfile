pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'build'
        ./gradlew clean build
      }
    }

    stage('upload') {
      steps {
        echo 'upload'
        aws s3 cp build/libs/delivery.jar "s3://jenkins-deploy-myeats/delivery.jar" --region ap-northeast-2
      }
    }

    stage('deploy') {
      steps {
        echo 'deploy'
        aws elasticbeanstalk create-application-version --region ap-northeast-2 --application-name myeats --version-label ${BUILD_TAG} --source-bundle S3Bucket="jenkins-deploy-myeats",S3Key="delivery.jar"
        aws elasticbeanstalk update-environment --region ap-northeast-2 --environment-name Myeats-env --version-label ${BUILD_TAG}
      }
    }

  }
}
