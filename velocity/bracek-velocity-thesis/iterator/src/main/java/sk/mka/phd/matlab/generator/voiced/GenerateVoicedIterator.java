package sk.mka.phd.matlab.generator.voiced;

import java.io.IOException;
import sk.mka.phd.matlab.generator.Generator;

/**
 *
 * @author bracek
 */
public class GenerateVoicedIterator {

    public static void main(String[] args) throws IOException {
        final Generator voicedVoicelessGenerator = new VoicedGeneratorImpl();
        voicedVoicelessGenerator.init();
    }
}
