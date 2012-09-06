package sk.mka.books.patterns.singleton;


/**
 *
 * @author bracek
 * @date Dec 22, 2009
 */
public class SpoolerRun {

    static public void main(String argv[]) {
        //open one printer--this should always work
        System.out.println("Opening one spooler");
        try {
        } catch (SingletonException e) {
            System.out.println(e.getMessage());
        }
        //try to open another printer --should fail
        System.out.println("Opening two spoolers");
        try {
        } catch (SingletonException e) {
            System.out.println(e.getMessage());
        }
    }
}
