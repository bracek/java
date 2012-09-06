package sk.mka.books.patterns.abstractfactory;

/**
 * The DicedGreekSaladInstructionFactory is used to illustrate the Abstract Factory Pattern of GoF. The 
 * DicedGreekSaladInstructionFactory implements the list of operations and create the concrete product objects that is 
 * associated with the specific concrete factory class.
 * 
 * @author Andre Mare
 */
public class DicedGreekSaladInstructionFactory implements SaladInstructionsKit {

    @Override
    public TomatoInstructions createTomatoInstructions() {
        return new DicedTomatoInstructions();
    }

    @Override
    public CucumberInstructions createCucumberInstructions() {
        return new DicedCucumberInstructions();
    }
} // class DicedGreekSaladInstructionFactory

