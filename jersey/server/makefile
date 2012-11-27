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




