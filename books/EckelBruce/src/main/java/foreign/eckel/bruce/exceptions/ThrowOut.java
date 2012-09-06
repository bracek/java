/**
 * trieda Throwable je bazovou triedou Exception => vyvolanim vynimky mozeme ziskat objekt typu Throwable
 * nie vsak typu Exception
 * handler typy Exception v metode main() ju nezachyti
 */
package foreign.eckel.bruce.exceptions;

/**
 *
 * @author bracek
 */
public class ThrowOut {

    public static void main(String[] args) throws Throwable {
        try {
            throw new Throwable();
        } catch (Exception e) {
            System.err.println("Zachytene v metode main");
        }
    }
}
