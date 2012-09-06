load "style.gp"
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphB.eps"
set xlabel "id neuronovej siete (fonema)"
set ylabel "priemerna uspesnost triedy B [%]" 
set xrange [0:61]
set yrange [0:105]
set style fill solid 0.25 border
set boxwidth 0.15
plot "input/result.txt" using 1:6 ls 3 with boxes title "priemerna  uspesnost neuronovych sieti pre jednotlive fonemy (trieda B) "
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"

