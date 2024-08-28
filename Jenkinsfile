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
        try {
            stage('Export gpg keys') {
                withCredentials([string(credentialsId: 'gpg_passphrase', variable: 'GPG_PASSPHRASE')]) {
                    sh('mkdir -p /tmp/gpg')
                    sh('gpg --batch --pinentry-mode=loopback --yes --passphrase ${GPG_PASSPHRASE} --export-secret-key -a "Info Bitbar" > /tmp/gpg/prv.key')
                    sh('gpg --export -a "Info Bitbar" > /tmp/gpg/pub.key')
                }
            }
            docker.image("maven:3.9.8-eclipse-temurin-17").inside("-u 0:0 -v /home/testdroid/.m2:/root/.m2 -v /tmp/gpg:/gpg") {

                stage('Deploy') {
                    wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'XTerm']) {
                        script {
                            sh('apt update && apt install -y gnupg')
                            sh('gpg --list-keys')
                            sh('gpg --batch --import /gpg/prv.key')
                            sh('gpg --batch --import /gpg/pub.key')
                            sh('sleep 5')
                            sh('gpg --list-keys')
                            def version = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                            def gh = new Github(this, 'testdroid-api', 'bitbar')
                            gh.release('master', 'v' + version, "Version ${version}")
                            sh('mvn clean package source:jar javadoc:jar gpg:sign nexus-staging:deploy')
                        }
                    }
                }
            }
        } finally {
            sh('rm -rf /tmp/gpg/')
        }
    }
}
