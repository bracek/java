run:
	mvn compile
	cp -v test.backup/sa1.par_out.csv test/
	mvn exec:java -Dexec.mainClass=sk.mka.phd.timit.SimplifyTimitApp
	firefox outputGroups.xml &
	#make less.outputGroupsRepresentation
	make less.outputGroups
less.outputGroups:
	less outputGroups.xml
less.outputGroupsRepresentation:
	less outputGroupRepresentation.txt
