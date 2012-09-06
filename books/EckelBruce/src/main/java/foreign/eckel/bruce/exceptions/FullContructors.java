package foreign.eckel.bruce.exceptions;

import foreign.eckel.bruce.exceptions.*;

/**
 *
 * @author bracek   
 */
class SimpleException extends Exception {

    public SimpleException() {
    }

    public SimpleException(String message) {
        super(message);
    }
}

public class FullContructors {

    public static void f() throws SimpleException {
        System.out.println("vyvolanie vynimky SimpleException z metody f()");
        throw new SimpleException();
    }

    public static void g() throws SimpleException {
        System.out.println("vyvolanie vynimky SimpleException z metody g()");
        throw new SimpleException("Povod vynimky je v metode g()");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            f();
        } catch (SimpleException e) {
            e.printStackTrace(System.err);
        }

        try {
            g();
        } catch (SimpleException e) {
            e.printStackTrace(System.err);
        }
    }
}
