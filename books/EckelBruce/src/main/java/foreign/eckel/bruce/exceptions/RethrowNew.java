/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foreign.eckel.bruce.exceptions;


/**
 *
 * @author bracek
 */
class VynimkaJenda extends Exception {

    public VynimkaJenda(String s) {
        super(s);
    }
}

class VynimkaDve extends Exception {

    public VynimkaDve(String s) {
        super(s);
    }
}

public class RethrowNew {

    public static void f() throws VynimkaJenda {
        System.out.println("povod vynimky je v tele metody f()");
        throw new VynimkaJenda("vyvolana v metode f()");

    }

    public static void main(String[] args)
            throws
            VynimkaDve {
        try {
            f();
        } catch (VynimkaJenda e) {
            System.err.println("zachytene v metode main, e.printStackTrace()");
            e.printStackTrace(System.err);
            throw new VynimkaDve(("z metody main()"));

        }
    }
}

