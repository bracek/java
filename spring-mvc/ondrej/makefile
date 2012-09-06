def:
	mvn install
clean:
	mvn clean
### BEGIN OF PROFILES ###
##kemt_repository ###
kemt.deploy:
	mvn -Pkemt_repository -Dmaven.test.skip=true clean deploy
kemt.javadoc:
	mvn -Pkemt_repository eclipse:eclipse -DdownloadJavadocs=true
kemt.sources:
	mvn -Pkemt_repository eclipse:eclipse -DdownloadSources=true
kemt.site:
	mvn -Pkemt_repository clean site
kemt.redeploy:
	mvn -Pkemt_repository tomcat:redeploy
kemt.package:
	mvn -Pkemt_repository clean package
### ixonos_artifactory repository ###
ixonos_artifactory.artifactory.test.skip:
	mvn -Pixonos_artifactory-Dmaven.test.skip=true
ixonos_artifactory.clean:
	mvn -Pixonos_artifactory-Dmaven.test.skip clean
ixonos_artifactory.deploy:
	mvn -Pixonos_artifactory -Dmaven.test.skip clean install deploy
ixonos_artifactory.package:
	mvn -Pixonos_artifactory -Dmaven.test.skip clean package
ixonos_artifactory.install:
	mvn -Pixonos_artifactory -Dmaven.test.skip clean install
ixonos_artifactory.javadoc:
	mvn -Pixonos_artifactory eclipse:eclipse -DdownloadJavadocs=true
ixonos_artifactory.eclipse:
	mvn -Pixonos_artifactory eclipse:eclipse
ixonos_artifactory.javasources:
	mvn -Pixonos_artifactory eclipse:eclipse -Declipse.downloadSources=true
ixonos_artifactory.site:
	mvn -Pixonos_artifactory site 
ixonos_artifactory.sonar:
	mvn -Pixonos_artifactory,sonar sonar:sonar
ixonos_artifactory.tomcat.build:
	mvn -Pixonos_artifactory -Dmaven.test.skip=true clean install
ixonos_artifactory.tomcat.undeploy:
	mvn -Pixonos_artifactory -Dmaven.test.skip=true tomcat:undeploy
ixonos_artifactory.tomcat.redeploy:
	mvn -Pixonos_artifactory -Dmaven.test.skip=true clean install tomcat:redeploy
### END OF PROFILES ###
