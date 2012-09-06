/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package foreign.eckel.bruce.exceptions;



/**
 *
 * @author bracek
 */
class FourtException extends Exception {
}

public class AlwaysFinally {

    public static void main(String[] args) {
        System.out.println("vstupujem do prveho bloku try");

        try {
            System.out.println("vstpujem do druheho bloku try");
            try {
                throw new FourtException();
            } finally {
                System.out.println("Klauzala finally vnutri druheho bloku try");
            }
        } catch (FourtException e) {
            System.out.println("VynimkaStyri zachytena vo vnutri prveho bloku try. ");
        } finally {
            System.err.println("Klauzula finally vo vnutri prveho bloku try");
        }
    }
}
           