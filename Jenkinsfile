pipeline {
    agent any

	environment {
	  LANG = 'en_US.UTF-8'
    LC_ALL = 'en_US.UTF-8'

    //Rbenv VM Path Configuration

    PATH = "/usr/local/opt/ruby/bin:/Users/ec2-user/.rbenv/shims:/opt/homebrew/bin:/opt/homebrew/sbin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin:/Library/Apple/usr/bin:$JAVA_HOME:$SSL_CERT_FILE"
    ANDROID_HOME = '/Users/ec2-user/Library/Android/sdk'
    SECRET_PROPERTY = ''
    GOOGLE_JSON = ''
	}

	parameters{
	  string(name: "VER_NAME", defaultValue: "1", trim: true, description: "Version name")
	  string(name: "VER_CODE", defaultValue: "01", trim: true, description: "Version code")
	  string(name:'BRANCH', defaultValue:'rnd/mqtt_location_tracker', trim:true, description:'Branch')
	  text(name: 'RELEASE_NOTES', defaultValue: "", description: 'Release Notes')
	  choice(name: 'PUBLISH_DOCS', defaultValue: false, description: 'Publish to Confluence')
	}

	options{
        timestamps()
        skipDefaultCheckout()
        disableRestartFromStage()
    }

	stages {
	stage('Checkout'){
    steps {
       checkout scmGit (
          branches: [[name: "${params.BRANCH}"]],
          extensions: [],
          userRemoteConfigs:
          [[credentialsId: '',
          url: 'https://github.com/EbelAlfie/Gastion.git']]
      )
    }
  }
  stage('Configure Environment') {
    steps {
        sh "gem install bundler"
        sh "bundle install"
        sh 'java --version'

        sh 'gem -v'
        sh "chmod +x gradlew"
    }
  }

   stage('Setup File'){
      steps{
          dir(env.WORKSPACE){
            sh "cp ${SECRET_PROPERTY} ${env.WORKSPACE}"
          }
      }
    }

    stage('Build APK') {
        steps {
            script{
              dir(env.WORKSPACE) {
                sh "fastlane build"
              }
            }
        }
    }

    stage('Firebase') {
      steps {
          sh "fastlane deploy_to_firebase VERSION_NAME:${VER_NAME} VERSION_CODE:${VER_CODE} RELEASE_NOTES:'${RELEASE_NOTES}'"
      }
    }

    stage('Publish Docs') {
      steps {

      }
    }

	}
	post {
      always {
          cleanWs()
          dir("${env.WORKSPACE}@tmp") {
              deleteDir()
          }
      }
  }
}