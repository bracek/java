### maven section ###
skip.test:
	make localhost.create
clean.install:
	mvn clean install
download.all:
	make download.javadoc
	make download.sources
make download.sources:
	mvn dependency:resolve -Dclassifier=sources
make download.javadoc:
	mvn dependency:resolve -Dclassifier=javadoc
localhost.validate: 
	mvn -Dmaven.test.skip=true -Plocalhost clean install
localhost.create:
	mvn  -Dmaven.test.skip=true -Plocalhost,create clean install
clean:
	mvn clean
	rm -rvf *~

### reporting section ###	
site:
	mvn site
	make preview.site
site.stage:
	mvn site:stage
preview.site:
	chrome target/site/index.html &
### end of reporting section ###
