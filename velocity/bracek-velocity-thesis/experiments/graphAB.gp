load "style.gp"
set encoding iso_8859_2
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphAB.eps"
set xlabel "id neuronovej siete (fonema)"
set ylabel "chyba klasifikacie triedy  A [%]" 
set xrange [0:61]
set yrange [0:105]
set style fill solid 0.25 border
set boxwidth 0.15
plot "input/result.txt" using 1:4 ls 4 with boxes title "chyba klasifikacie triedy (trieda A) "
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"
