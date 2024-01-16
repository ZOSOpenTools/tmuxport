node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/tmuxport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/tmuxport.git'), string(name: 'PORT_DESCRIPTION', value: 'a terminal multiplexer: it enables a number of terminals to be created, accessed, and controlled from a single screen.' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
