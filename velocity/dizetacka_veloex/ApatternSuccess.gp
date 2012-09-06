load "style.gp"
set encoding iso_8859_2
set terminal postscript color solid
set terminal postscript eps
set terminal postscript enhanced
set output "ApatternSuccess.eps"
set xlabel "pocet trenovacich prikladov triedy A" 
set ylabel "uspesnost triedy A [%]"
set yrange [70:101]
set style fill solid 0.25 border
set pointsize 3 
plot "input/result.txt" using 5:3 ls 2 with points notitle 
#plot "input/result.txt" using 5:3 ls 2 with points title "uspesnost triedy A v zavislosti od poctu trenovacich prikladov"
#pause -1 "porovnanie poctu vektorov pre jednotlive fonemy"

