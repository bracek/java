load "style.gp"
set encoding iso_8859_2
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "graphPhonemeRepresentationAll.eps"
set xrange [0:61]
#set yrange [0:105]
set style fill solid 0.25 border
set boxwidth 0.15
plot "input/phonemeRepresentationAll.txt" using 1:2 ls 1 with lines title "Pocet trenovacich vektorov reprezenujuce fonemu v databaze TIMIT",\
  "input/phonemeRepresentationAllTest.txt" using 1:2 ls 2 with lines title "Pocet testovacich  vektorov reprezenujuce fonemu v databaze TIMIT 
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"
