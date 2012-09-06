package sk.mka.books.patterns.abstractfactory;

/**
 * @author katrami
 *
 */
public class MainClass {

    public static void main(String[] args) {
        GreekSaladInstructionsClient c = new GreekSaladInstructionsClient(new DicedGreekSaladInstructionFactory());
        c.printGreekSaladInstructions();

        GreekSaladInstructionsClient c1 = new GreekSaladInstructionsClient(new SlicedGreekSaladInstructionFactory());
        c1.printGreekSaladInstructions();
    }
}
