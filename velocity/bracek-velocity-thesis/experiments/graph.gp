set terminal postscript color solid
load "style.gp"
set terminal postscript eps
set terminal postscript enhanced
set output "graph.eps"
set xlabel "id neuronovej siete (fonema)"
set xrange [0:61]
set ylabel "priemerna uspesnost NN-sieti pre i-tu fonemu (class A + class B)/2 [%]" 
set yrange [50:100]
set style fill solid 0.25 border
set boxwidth 0.15
set style line
plot "input/result.txt" using 1:2 ls 1 with boxes  title "priemerna uspesnost NN-sieti pre i-tu fonemu "
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"
