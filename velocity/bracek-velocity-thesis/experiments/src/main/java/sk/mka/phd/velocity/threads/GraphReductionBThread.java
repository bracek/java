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
public class GraphReductionBThread extends Thread {

    public GraphReductionBThread(final String jmeno) {
        super(jmeno);
    }

    @Override
    public void run() {

        try {
            System.out.println(getName());

            Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_REDUCTIONB);
            Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_REDUCTIONB);
            new File(Const.GENERAL_OUTPUT.OUTPUT_REDUCTIONB).exists();
            yield();
            if (interrupted() == true) {
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphReductionBThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
