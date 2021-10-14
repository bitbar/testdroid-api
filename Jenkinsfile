#!/usr/bin/env groovy

@Library('CloudOrchestrationLibrary')
import com.bitbar.Github

node('linux && maven && gpg') {
    ansiColor('xterm') {
        stage('Checkout') {
            checkout([
                    $class           : 'GitSCM',
                    branches         : [
                            [
                                    name: 'master'
                            ]
                    ],
                    userRemoteConfigs: [
                            [
                                    credentialsId: 'f652697e-beb7-4724-b1b5-4913a2bf45f5',
                                    url          : 'git@github.com:bitbar/testdroid-api.git'
                            ]
                    ]
            ])
        }
        stage('Deploy') {
            wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'XTerm']) {
                script {
                    def version = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                    def gh = new Github(this, 'testdroid-api', 'bitbar')
                    gh.release('master', 'v' + version, "Version ${version}")
                    sh('mvn clean package source:jar javadoc:jar gpg:sign deploy:deploy -DaltDeploymentRepository=ossrh::default::https://oss.sonatype.org/service/local/staging/deploy/maven2/')
                }
            }
        }
    }
}
