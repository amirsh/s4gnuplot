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
  def main(args: Array[String]) {
    import s4gnuplot.Gnuplot
    val terminal = 
    // "pdf"
    "pngcairo transparent"
    Gnuplot.run(s"""
set terminal $terminal enhanced font "Arial,12" fontscale 1.0 size 800, 600 
set output 'simple_selection.png'
set key inside left top vertical noreverse noenhanced autotitles
set datafile missing '-'
set datafile separator ","
set style data linespoints
set xlabel "# of Iterations"
set ylabel "Time (ms)"
set xtics border in scale 1,0.5 nomirror offset character 0, 0, 0 autojustify
set xtics  norangelimit font ",8"
set xtics   ()
set title "Simple Selection" 
set title font ",16,bold" 
n = 4
plot 'Selection.equality.csv' using 2:xtic(1) title columnheader(2), for [i=3:4] '' using i title columnheader(i)
    """)
  }
}