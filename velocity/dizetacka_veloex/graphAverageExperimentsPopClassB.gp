load "style.gp"
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphAverageExperimentsPopClassB.eps"
set xlabel "velkost populacie triedy B [%]"
set ylabel "uspesnost NN-sieti pre neznele fonemy od velkosti populacie triedy B [%]"
set yrange [0:110]
set style fill solid 0.5 border
show style line
plot "graphAverageExperimentsPopClassB.dat" using 1:2 ls 1 with lines title "priemerna uspesnost NN-sieti pre neznele fonemy",\
   "graphAverageExperimentsPopClassB.dat" using 1:3 ls 2 with lines title "priemerna uspesnost experimentov pre neznele fonemy triedy A", \
   "graphAverageExperimentsPopClassB.dat" using 1:4 ls 3 with lines title "priemerna uspesnost experimentov  pre neznele fonemy triedy B"   
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"
#set key left box
#set key right nobox



