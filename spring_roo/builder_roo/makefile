r:
	mvn clean jetty:run
d:
	mvnDebug clean  jetty:run 
e:
	make c
	mvn eclipse:eclipse
c: 
	mvn clean 
i:
	mvn clean
	mvn install
da:
	make download.javadoc
	make download.sources
make download.sources:
	mvn dependency:resolve -Dclassifier=sources
make download.javadoc:
	mvn dependency:resolve -Dclassifier=javadoc
