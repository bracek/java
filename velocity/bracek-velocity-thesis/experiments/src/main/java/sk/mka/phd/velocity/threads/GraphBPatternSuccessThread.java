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
public class GraphBPatternSuccessThread extends Thread {

    public GraphBPatternSuccessThread(final String jmeno) {
        super(jmeno);
    }

    @Override
    public void run() {

        try {
            System.out.println(getName());

            Runtime.getRuntime().exec(Const.GNUPLOT.BPATTERN_SUCCESS_GP);
            Runtime.getRuntime().exec(Const.EPSTOPDF.BPATTERN_SUCCESS_EPS);
            new File(Const.GENERAL_OUTPUT.BPATTERN_SUCCESS).exists();
            yield();
        } catch (IOException ex) {
            Logger.getLogger(GraphBPatternSuccessThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
