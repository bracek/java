load "style.gp"
set encoding iso_8859_2
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "BpatternSuccess.eps"
set xlabel "pocet trenovacich prikladov triedy B" 
set ylabel "uspesnost triedy B [%]"
set yrange [70:101]
set style fill solid 0.25 border
set pointsize 3
plot "input/result.txt" using 6:4 ls 3 with points notitle 
#plot "input/result.txt" using 6:4 ls 3 with points title "uspesnost triedy B v zavislosti od poctu trenovacich prikladov"
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"

