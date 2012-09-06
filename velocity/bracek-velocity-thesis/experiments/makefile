default:
	make graph.dependency.input.params.mc
#	make average.experiments
#	make tex.generate
compile:
	mvn compile
sonar:
	mvn sonar:sonar
install:
	mvn clean install
bpMomentumaverage.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunBpMomentumFonikAverageExperiments
	make pdfcslatex

average.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunAverageExperiments 
	make pdfcslatex
voiced.average.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunVoicedAverageExperiments 
	make pdfcslatex
unvoiced.average.experiments:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.experiments.average.RunUnvoicedAverageExperiments
	make pdfcslatex
tex.generate:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateResults2latex
	make pdfcslatex
tex.voiced:
	make compile
	mvn exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenerateTableVoicedUnvoiced
	make pdfcslatex
tex.experiments:
	make compile
	mvn  exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenExperimentsResults2latex
	make pdfcslatex
tex.phonemelist:
	make compile
	mvn  exec:java -Dexec.mainClass=sk.mka.phd.velocity.GenPhonemeList
	make pdfcslatex

### rest of independent target ###
less.log:
	less target/phd.log
graph.average.experiments:
	gnuplot graphAverageExperimentsPopClassB.gp
	epstopdf graphAverageExperimentsPopClassB.eps
	gv graphAverageExperimentsPopClassB.eps

### graph dependedncy on input NN params ###
graph.dependency.input.params.mc:
	gnuplot graphDependencySuccessOnMc.gp
	gv graphDependencySuccessOnMc.eps
graph.dependency.input.params.lr:
	gnuplot graphDependencySuccessOnLr.gp
	gv graphDependencySuccessOnLr.eps
#### end of dependedncy on input NN params ###

graph:
	gnuplot graph.gp
	epstopdf graph.eps
	gv graph.eps
graph.a:
	gnuplot graphA.gp
	epstopdf graphA.eps
	evince graphA.pdf
graph.ab:
	gnuplot graphAB.gp
	epstopdf graphAB.eps
	evince graphAB.pdf
graph.ba:
	gnuplot graphBA.gp
	epstopdf graphBA.eps
	evince graphBA.pdf
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
doxygen:
	doxygen Doxyfile
	firefox html/index.html &
doxygen.tex:
	doxygen Doxyfile
	cd latex/ &&  make pdf && evince refman.pdf &
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
	rm -rvf *~
	rm -rvf *.out
