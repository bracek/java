clean.install:
	mvn clean install -Dmaven.test.skip=true
javadoc:
	mvn eclipse:eclipse -DdownloadJavadocs=true
sources:
	mvn eclipse:eclipse -DdownloadSources=true
site:
	mvn clean site
	make preview
preview:
	chromium-browser target/site/index.html &
eclipse:
	mvn clean eclipse:eclipse
jetty.debug:
	export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
	mvn jetty:run






