load "style.gp"
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphDependencySuccessOnLr.eps"
set xlabel "hodnota parametrov" 
set ylabel "priemerna uspesnost [%]"
set xrange [0:0.4]
set yrange [75:100]
set style fill solid 0.25 border
set pointsize 2
set boxwidth 0.15
plot "graphAverageExperimentsPopClassB.dat" using 6:2 ls 1 with points title "primerna uspesnost NN-sieti v zavisloti od lr",\
       "graphAverageExperimentsPopClassB.dat" using 6:3 ls 2 with points title "priemerna uspesnost triedy A v zavislosti od lr",\
       "graphAverageExperimentsPopClassB.dat" using 6:4 ls 3 with points title "priemerna uspesnost triedy B v zavislosti od lr"
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"



