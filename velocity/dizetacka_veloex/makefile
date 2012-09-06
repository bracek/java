default:
	make tex.generate
sonar:
	mvn sonar:sonar
compile:
	mvn compile
install:
	mvn clean install
voiced.average.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunVoicedAverageExperiments 
	make pdfcslatex
unvoiced.average.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunUnvoicedAverageExperiments
	make pdfcslatex
tex.generate:
	mvn exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateResults2latex
	make pdfcslatex
tex.voiced:
	mvn exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateTableVoicedUnvoiced
	make pdfcslatex
tex.experiments:
	mvn  exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenExperimentsResults2latex
	make pdfcslatex

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

### rest of independent target ###
less.log:
	less target/phd.log
graph.average.experiments:
	gnuplot graphAverageExperimentsPopClassB.gp
	epstopdf graphAverageExperimentsPopClassB.eps
	gv graphAverageExperimentsPopClassB.eps
graph:
	gnuplot graph.gp
	epstopdf graph.eps
	gv graph.eps
graph.a:
	gnuplot graphA.gp
	epstopdf graphA.eps
	evince graphA.pdf
graph.b:
	gnuplot graphB.gp
	epstopdf graphB.eps
	evince graphB.pdf
ApatternSuccess:
	gnuplot ApatternSuccess.gp
	epstopdf ApatternSuccess.eps
	gv ApatternSuccess.eps
BpatternSuccess:
	gnuplot BpatternSuccess.gp
	epstopdf BpatternSuccess.eps
	gv BpatternSuccess.eps
pdfcslatex:
	pdfcslatex table.tex
	make preview
agraph:
	make graph.a
	evince graphA.pdf &
tex:
	mvn compile
	ant
	java -jar target/veloex-0.0.1-SNAPSHOT.jar
	make pdfcslatex
	make preview
download.files:
	cp -v ~/subversion/kemt/katrak/matlab/out/input_params.txt input/
	cp -v ~/subversion/kemt/katrak/matlab/out/pattern.txt input/
	cp -v ~/subversion/kemt/katrak/matlab/out/result.txt input/
	cp -v ~/subversion/kemt/katrak/matlab/out/topology.txt input/
preview:
	evince table.pdf &
clean:
	mvn clean
	du -sh
	find . -type f -name ".*" -exec rm -f {} \;
	rm -rvf *.aux
	rm -rvf *.dat
	rm -rvf *.eps
	rm -rvf *.dvi
	rm -rvf *.lo[gtf]
	rm -rvf *.p[ds]f
	rm -rvf *.out
