package sk.mka.phd.velocity.threads;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.mka.phd.matlab.cons.Const;

/**
 *
 * @author bracek
 * @date Feb 4, 2010
 */
public class GraphAThread extends Thread {

    public GraphAThread(final String jmeno) {
        super(jmeno);
    }

    @Override
    public void run() {

        try {
            System.out.println(getName());
            Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_GRAPH_A_GP);
            Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_GRAPH_A_EPS);
            new File(Const.GENERAL_OUTPUT.OUTPUT_GRAPH_CLASS_A).exists();
            yield();
        } catch (IOException ex) {
            Logger.getLogger(GraphAThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
