load "style.gp"
set encoding iso_8859_2
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphA.eps"
set xlabel "id neuronovej siete (fonema)"
set ylabel "celkova uspesnost triedy A [%]" 
set xrange [0:61]
set yrange [0:105]
set style fill solid 0.25 border
set boxwidth 0.15
plot "outputA.dat" using 1:2 ls 2 with boxes title "celkova uspesnost neuronovych sieti pre jednotlive fonemy (trieda A) "
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"
