package s4gnuplot

object Test {
  def main(args:Array[String]) {
    import s4gnuplot.Gnuplot
    Gnuplot.newPlot
      .dataInt(
        (1 to 5).map { i => Seq(i, i + 4, i + 3 - (math.random * 3 toInt), i + 7 + (math.random * 5 toInt)) }
      )
      .title("Hello")
      .xrange(0 to 6)
      .xlabel("Grass")
      .ylabel("Rabbits")
      .gridOn
      .custom("set title 'Rabbits & grass'")
      .line(_.title("std. err").With("yerrorbars").using("1:2:3:4").style("ps 0.1"))
      .line(_.title("ordinary rabbit").With("linespoints").using("1:2").style("pt 7 ps 0.8"))
      .plot
      
    println("Done.")
  }
}

object Test2 {
  import s4gnuplot.Gnuplot
  Gnuplot.run("""
    set terminal pngcairo  transparent enhanced font "arial,10" fontscale 1.0 size 500, 350 
set output 'histograms.2.png'
set key inside right top vertical Right noreverse noenhanced autotitles nobox
set datafile missing '-'
set style data linespoints
set xlabel "# of Iterations"
set ylabel "Time (ms)"
set xtics border in scale 1,0.5 nomirror offset character 0, 0, 0 autojustify
set xtics  norangelimit font ",8"
set xtics   ()
set title "Simple Selection" 
i = 22
plot 'immigration.dat' using 2:xtic(1) title columnheader(2), for [i=3:22] '' using i title columnheader(i)
    """)
}