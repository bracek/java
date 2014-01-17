package sk.mka.phd.matlab.generator.unvoiced;

import sk.mka.phd.matlab.generator.Generator;
import java.io.IOException;

/**
 *
 * @author katrami
 */
public class GenerateUnvoicedIterator {

    public static void main(final String[] args) throws IOException {
        final Generator unovicedGenerator = new UnvoicedGeneratorImpl();
        unovicedGenerator.init();
    }
}
