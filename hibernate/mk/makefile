### BEGIN OF PROFILES ###
##kemt ###
clean.install:
	mvn clean install
sonar:
	mvn sonar:sonar
default:
	make kemt.install
kemt.install:
	mvn -Pdevel,kemt clean install
kemt.deploy:
	mvn -Pdevel,kemt -Dmaven.test.skip=true clean deploy
kemt.javadoc:
	mvn -Pkemt eclipse:eclipse -DdownloadJavadocs=true
kemt.sources:
	mvn -Pkemt eclipse:eclipse -DdownloadSources=true
kemt.site:
	mvn -Pkemt clean site
kemt.tomcat.deploy:
	mvn -Pkemt-Dmaven.test.skip=true tomcat:deploy
kemt.tomcat.undeploy:
	mvn -Pixonos -Dmaven.test.skemtip=true tomcat:undeploy
kemt.tomcat.redeploy:
	mvn -Pixonos -Dmaven.test.skemtip=true clean install tomcat:undeploy tomcat:deploy
kemt.package:
	mvn -Pkemt clean package

kemt.dependency.tree:
	mvn -Pkemt dependency:tree -Dverbose 
	#mvn -Pkemt dependency:tree -Dverbose -Dincludes=commons-collections
### kemt,create 
kemt.create.install:
	mvn -Pdevel,kemt,create clean install
### ixonos repository ###
ixonos.artifactory.test.skemtip:
	mvn -Pixonos-Dmaven.test.skemtip=true
ixonos.clean:
	mvn -Pixonos-Dmaven.test.skemtip clean
ixonos.deploy:
	mvn -Pixonos -Dmaven.test.skemtip clean install deploy
ixonos.packemtage:
	mvn -Pixonos -Dmaven.test.skemtip clean package
ixonos.install:
	mvn -Pixonos -Dmaven.test.skemtip clean install
ixonos.javadoc:
	mvn -Pixonos eclipse:eclipse -DdownloadJavadocs=true
ixonos.eclipse:
	mvn -Pixonos eclipse:eclipse
ixonos.javasources:
	mvn -Pixonos eclipse:eclipse -Declipse.downloadSources=true
ixonos.site:
	mvn -Pixonos site 
ixonos.sonar:
	mvn -Pixonos,sonar sonar:sonar
ixonos.tomcat.build:
	mvn -Pixonos -Dmaven.test.skemtip=true clean install
ixonos.tomcat.deploy:
	mvn -Pixonos -Dmaven.test.skemtip=true tomcat:deploy
ixonos.tomcat.undeploy:
	mvn -Pixonos -Dmaven.test.skemtip=true tomcat:undeploy
ixonos.tomcat.redeploy:
	mvn -Pixonos -Dmaven.test.skemtip=true clean install tomcat:redeploy
### END OF PROFILES ###
clean:
	mvn clean 

