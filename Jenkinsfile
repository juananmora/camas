node {
    stage ('CheckOut GitHub') {
        
     	 checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/juananmora/camas.git']]]) 
	}
    stage ('Build') {
         withMaven(maven: 'mvn 3.6.3') {
	    sh "mvn clean package" 
	 }
    }
	   docker.withTool("docker") { 
		withDockerServer([credentialsId: "", uri: "unix:///var/run/docker.sock"]) { 
      
			stage ('Build Image'){
				sh "docker build -t juananmora/camas:'$BUILD_NUMBER' ."
				sh "docker login -u juananmora -p xxxx"
				sh "docker push juananmora/camas:'$BUILD_NUMBER'"
				sh "docker image rm juananmora/camas:'$BUILD_NUMBER'"
	//			sleep(200)
			 }

		}
    }
	
}
