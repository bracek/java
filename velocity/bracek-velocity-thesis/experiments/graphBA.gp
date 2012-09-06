load "style.gp"
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphBA.eps"
set xlabel "id neuronovej siete (fonema)"
set ylabel "chyba i-tej fonemy triedy B [%]" 
set xrange [0:61]
set yrange [0:105]
set style fill solid 0.25 border
set boxwidth 0.15
plot "input/result.txt" using 1:5 ls 5 with boxes title "chyba i-tej fonemy treidy B "
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"

