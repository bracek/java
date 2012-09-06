do:
	mvn clean install
clean:
	mvn clean 
download.javadoc:
	mvn eclipse:eclipse -DdownloadJavadocs=true
site:
	mvn site
	firefox target/site/index.html &
	firefox simplify-core/target/site/index.html &
depgraph:
	mvn depgraph:depgraph
	gqview logic/target/site/images/skillnet-logic.png &
	gqview webapp/target/site/images/skillnet-webapp.png &
fullgraph:
	mvn depgraph:fullgraph
	gqview target/site/images/SKILLNET.png &
