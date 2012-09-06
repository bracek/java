/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foreign.eckel.bruce.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author bracek
 */
public class DirList {

    public static void main(String[] args) {

        File cesta = new File(".");
        String[] zoznam = null;

        if (args.length == 0) {
            zoznam = cesta.list();
        } else {
            zoznam = cesta.list((FilenameFilter) new DirFilter(args[0]));//        Arrays.sort(zoznam, new AlphabeticComparator());
        }

        for (int i = 0; i < zoznam.length; i++) {
            String string = zoznam[i];
            System.out.println("zoznam = " + string);
        }
    }
}

class DirFilter implements FilenameFilter {

    String filter;

    public DirFilter(String filter) {
        this.filter = filter;
    }

    public boolean accept(File dir, String name) {
        String f = new File(name).getName();
        return f.indexOf(filter) != -1;

    }
}



