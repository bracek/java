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
public class GenerateGraphThread extends Thread {

    public GenerateGraphThread(String jmeno) {
        super(jmeno);
    }

    @Override
    public void run() {

        try {
            System.out.println(getName());
            Runtime.getRuntime().exec(Const.GNUPLOT.GNUPLOT_GRAPH_GENERAL_GP); //generate graph which will be included to table.tex later
            Runtime.getRuntime().exec(Const.EPSTOPDF.EPSTOPDF_GRAPH_GENERAL_EPS); //generate graph which will be included to table.tex later
            new File(Const.GENERAL_OUTPUT.OUTPUT_GRAPH).exists();
            yield();
            if (interrupted() == true) {
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger(GenerateGraphThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
