package foreign.eckel.bruce.exceptions;

import foreign.eckel.bruce.exceptions.*;

/**
 *
 * @author bracek
 */
class SimpleException_13 extends Exception {
}

public class SimpleExceptionDemo_13a {

    public  void f() throws SimpleException_13 {
        System.out.println("vyvolanie vyninmky JednoduchaVynimka z metody f()");
        throw new SimpleException_13();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SimpleExceptionDemo_13a sed = new SimpleExceptionDemo_13a();
        try {
            sed.f();
        } catch (SimpleException_13 e) {
            System.err.println("Vynimka zachytena");
        }
    }
}
