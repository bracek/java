package foreign.eckel.bruce.exceptions;

public class Rethrowing {

    public static void f() throws Exception {
        System.out.println("povod vynimky je v metode f()");
        throw new Exception("vyvolana z metody f()");

    }

    public static void g() throws Throwable {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Uvnitr g(),  + e.printStackTrace()");
            e.printStackTrace(System.err);
            throw e;
        }
    }

    public static void main(String[] args) throws Throwable {
        try {
            g();
        } catch (Exception e) {
            System.err.println("Zachytene v metode main, e.printTrackTrace()");
            e.printStackTrace(System.err);
        }
    }
}
