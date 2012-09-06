load "style.gp"
#set key inside right top vertical Right noreverse enhanced autotitles box linetype -1 linewidth 1.000
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphDependencySuccessOnMc.eps"
set xlabel "hodnota parametrov" 
set ylabel "priemerna uspesnost [%]"
set xrange [0.45:1] noreverse nowriteback
set yrange [75:100] noreverse nowriteback
set style fill solid 0.25 noborder
set pointsize 2
set boxwidth 0.05 
set  style data lines
plot "graphAverageExperimentsPopClassB.dat" u 5:2 ls 1 w points title "primerna uspesnost NN-sieti v zavisloti od mc",\
       "graphAverageExperimentsPopClassB.dat" u 5:3 ls 2 w points title "priemerna uspesnost triedy A v zavislosti od mc",\
       "graphAverageExperimentsPopClassB.dat" u 5:4 ls 3 w points title "priemerna uspesnost triedy B v zavislosti od mc"
replot
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"



