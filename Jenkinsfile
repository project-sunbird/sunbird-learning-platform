node('build-slave') {
    try {
        String ANSI_GREEN = "\u001B[32m"
        String ANSI_NORMAL = "\u001B[0m"
        String ANSI_BOLD = "\u001B[1m"
        String ANSI_RED = "\u001B[31m"
        String ANSI_YELLOW = "\u001B[33m"

        if (params.size() == 0){
            properties([[$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false], parameters([string(defaultValue: '', description: '<font color=teal size=2>If you want to build from a tag, specify the tag name. If this parameter is blank, latest commit hash will be used to build</font>', name: 'tag', trim: false)])])

            ansiColor('xterm') {
                println (ANSI_BOLD + ANSI_GREEN + '''\
                        First run of the job. Parameters created. Stopping the current build.
                        Please trigger new build and provide parameters if required.
                        '''.stripIndent().replace("\n"," ") + ANSI_NORMAL)
            }
            return
        }

        ansiColor('xterm') {
            stage('Checkout') {
                cleanWs()
                if(params.tag == ""){
                    checkout scm
                    commit_hash = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    branch_name = sh(script: 'git name-rev --name-only HEAD | rev | cut -d "/" -f1| rev', returnStdout: true).trim()
                    artifact_version = branch_name + "_" + commit_hash
                }
                else {
                    def scmVars = checkout scm
                    checkout scm: [$class: 'GitSCM', branches: [[name: "refs/tags/$params.tag"]],  userRemoteConfigs: [[url: scmVars.GIT_URL]]]
                    artifact_version = params.tag
                }
                echo "artifact_version: "+ artifact_version
            }
        }

            stage('Pre-Build') {
                sh """
                java -version
                rm -rf /data/logs/*
                rm -rf /data/graphDB/*
                rm -rf /data/testgraphDB/*
                rm -rf /data/testGraphDB/*
                vim -esnc '%s/dialcode.es_conn_info="localhost:9200"/dialcode.es_conn_info="10.6.0.11:9200"/g|:wq' platform-core/unit-tests/src/test/resources/application.conf
                vim -esnc '%s/search.es_conn_info="localhost:9200"/search.es_conn_info="10.6.0.11:9200"/g|:wq' platform-core/unit-tests/src/test/resources/application.conf
                vim -esnc '%s/search.es_conn_info="localhost:9200"/search.es_conn_info="10.6.0.11:9200"/g|:wq' searchIndex-platform/module/search-api/search-manager/conf/application.conf
                """
            }

            stage('Build') {
                sh 'mvn clean install -DskipTests'
            }

            stage('Post-Build') {
                sh """
                        cd searchIndex-platform/module/search-api/search-manager
                        mvn play2:dist
                     """
            }

            stage('Post_Build-Action') {
                jacoco exclusionPattern: '**/common/**,**/dto/**,**/enums/**,**/pipeline/**,**/servlet/**,**/interceptor/**,**/batch/**,**/models/**,**/model/**,**/EnrichActor*.class,**/language/controller/**,**/wordchain/**,**/importer/**,**/Base**,**/ControllerUtil**,**/Indowordnet**,**/Import**'

            }

            stage('Archive artifacts'){
                sh """
                        mkdir lp_artifacts
                        cp platform-modules/service/target/learning-service.war lp_artifacts
                        cp searchIndex-platform/module/search-api/search-manager/target/search-manager*.zip lp_artifacts
                        zip -j lp_artifacts.zip:${artifact_version} lp_artifacts/*
                    """
                archiveArtifacts artifacts: "lp_artifacts.zip:${artifact_version}", fingerprint: true, onlyIfSuccessful: true
                sh """echo {\\"artifact_name\\" : \\"lp_artifacts.zip\\", \\"artifact_version\\" : \\"${artifact_version}\\", \\"node_name\\" : \\"${env.NODE_NAME}\\"} > metadata.json"""
                archiveArtifacts artifacts: 'metadata.json', onlyIfSuccessful: true
            }
        }

    catch (err) {
        currentBuild.result = "FAILURE"
        throw err
    }

}
