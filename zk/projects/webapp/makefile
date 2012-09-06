def:
	cd .. && make && cd webapp 
	make clean
#	make lcjr
	make dlcjr

### maven goals ###
# lcjr = localhost create jetty run
lcjr:
	mvn clean -Plocalhost,create jetty:run 
# debug localhost create jetty run
dlcjr:
	mvnDebug clean -Plocalhost,create jetty:run 
# localhost validate jetty run
lvjr:
	mvn clean -Plocalhost jetty:run 
# debug localhost validate jetty run
dlvjr:
	mvnDebug clean -Plocalhost
	jetty:run ps ax
clean:
	rm -rf jetty_webapp.log
	rm -rvf *~
