package sk.mka.phd.time;

/**
 *
 * @author bracek
 * @date Jul 22, 2010
 */
public class ProgramTime {

    private final long begin;
    private final long beginNano;

    ProgramTime() {
        begin = System.currentTimeMillis();
        beginNano = System.nanoTime();

    }

    void end() {
        long kn = System.nanoTime();
        long k = System.currentTimeMillis();

        System.out.print((k - begin) + "\t");
        System.out.print(((kn - beginNano) / 1000000) + "\t");

    }
}
