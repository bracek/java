def:
	make clean 
	make kemt
ixonos:
	mvn clean -Dmaven.test.skip=true -Pdevel,artifactory jetty:run > jetty_webapp.log  |less
kemt:
	mvn clean -Dmaven.test.skip=true -Pdevel,kemt jetty:run > jetty_webapp.log |less
kemt.create.install:
	mvn clean -Dmaven.test.skip=true -Pdevel,kemt,create jetty:run > jetty_webapp.log |less
jetty.run:
	mvn -Dmaven.test.skip=true -Pdevel,create jetty:run
psax:
	ps ax|grep jetty
clean:
	rm -rf jetty_webapp.log
