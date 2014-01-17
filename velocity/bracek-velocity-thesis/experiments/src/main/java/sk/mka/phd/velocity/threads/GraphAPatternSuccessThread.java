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
public class GraphAPatternSuccessThread extends Thread {

    public GraphAPatternSuccessThread(final String jmeno) {
        super(jmeno);
    }

    @Override
    public void run() {

        try {
            System.out.println(getName());


            Runtime.getRuntime().exec(Const.GNUPLOT.APATTERN_SUCCESS_GP);
            Runtime.getRuntime().exec(Const.EPSTOPDF.APATTERN_SUCCESS_EPS);
            new File(Const.GENERAL_OUTPUT.APATTERN_SUCCESS).exists();
            yield();
            if (interrupted() == true) {
                return;
            }

        } catch (IOException ex) {
            Logger.getLogger(GraphAPatternSuccessThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
