generate.iterator.matlab:
	mvn compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.all.GenerateIterator
generate.voiced.iterator:
	mvn compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.voiced.GenerateVoicedIterator
generate.unvoiced.iterator:
	mvn compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.unvoiced.GenerateUnvoicedIterator
### using kemt_artifactory profile ###
arti.tex.generate:
	mvn -Partifactory_kemt -e exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateResults2latex
	make pdfcslatex
arti.tex.voiced:
	mvn -Partifactory_kemt -e exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateTableVoicedUnvoiced
	make pdfcslatex
arti.generate.iterator.matlab:
	mvn -Partifactory_kemt compile
	mvn -Partifactory_kemt exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.all.GenerateIterator
arti.generate.voiced.iterator:
	mvn  -Partifactory_kemt  compile
	mvn -Partifactory_kemt exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.voiced.GenerateVoicedIterator
arti.generate.unvoiced.iterator:
	mvn -Partifactory_kemt compile
	mvn -Partifactory_kemt exec:java -Dexec.mainClass=sk.mka.phd.matlab.generator.unvoiced.GenerateUnvoicedIterator
arti.tex.experiments:
	mvn -Partifactory_kemt  exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenExperimentsResults2latex
	make pdfcslatex
