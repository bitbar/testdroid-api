#!/usr/bin/env groovy
node('linux && maven && gpg') {
    ansiColor('xterm') {
        stage('Checkout') {
            checkout([
                    $class           : 'GitSCM',
                    branches         : [
                            [
                                    name: env.BRANCH
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

        stage('Build') {
            sh('mvn clean package source:jar javadoc:jar gpg:sign deploy:deploy -DaltDeploymentRepository=ossrh::default::https://oss.sonatype.org/service/local/staging/deploy/maven2/')
        }
    }
}
