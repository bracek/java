package sk.mka.books.patterns.factorymethod;

/**
 * The BankAccountCreator is used to illustrate the Factory Method Pattern of GoF. The BankAccountCreator
 * is the abstract creator class that defines the factory method. The implementation of the factory methods
 * is delegated to the subclasses of the abstract BankAccountCreator.
 * 
 * @author Andre Mare
 */
public abstract class BankAccountCreator {

    protected abstract BankAccountProduct createBankAccount(String accountType);
} // class BankAccountCreator

