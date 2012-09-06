load "style.gp"
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "reductionB.eps"
set title "redukcia vektorov triedy B" 
set xrange [0:61]
#set yrange [0:2800]
set style fill solid 0.25 border
set boxwidth 0.15
plot "input/desiredPopulationClassB.dat" using 1:2 ls 3 with boxes title "pocet vektorov triedy B fonemu pred redukciou", \
     "input/realPopulationClassB.dat" using 1:2 ls 1 with boxes title "pocet vektorov triedy B pre po redukcii "
#pause -1 "redukcia vektorov triedy B" 
