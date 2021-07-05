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
        stage('Deploy to nexus.wro.int.bitbar.com or oss.sonatype.org') {
            if (env.BRANCH == 'devel' || env.BRANCH == 'master') {
                deploy("${env.BRANCH_NAME}-${env.BUILD_ID}", 'testdroid-api-internal-nexus-deploy')
            } else {
                sh('mvn clean package source:jar javadoc:jar gpg:sign deploy:deploy -DaltDeploymentRepository=ossrh::default::https://oss.sonatype.org/service/local/staging/deploy/maven2/')
            }
        }
    }
}

def deploy(uniquePrefix, postfix) {
    wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'XTerm']) {
        def containerName = uniquePrefix + postfix + '-mvn'
        try {
            sh("""
                docker run -i --rm --name $containerName \
                -v \"\$(pwd)\":/usr/src/testdroid-master \
                -v /home/testdroid/.m2:/root/.m2 \
                -w /usr/src/testdroid-master maven:3.8.1-jdk-8 \
                mvn clean deploy -DskipTests -DaltDeploymentRepository=nexus::default::https://nexus.wro.int.bitbar.com/repository/releases/ \
                -Dmaven.color=true \
            """)
        } finally {
            //cleanup, notification etc...
            sh('sudo chown -R $(whoami):$(whoami) target')
        }
    }
}
