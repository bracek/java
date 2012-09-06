package sk.mka.books.patterns.factorymethod;

/**
 * The BankAccountProduct is used to illustrate the Factory Method Pattern of GoF. The BankAccountProduct
 * is the abstract product class that defines the concrete product classes that are created by the
 * factory methods.
 * 
 * @author Andre Mare
 */
public abstract class BankAccountProduct {

    public abstract void depositMoney(double depositAmount);

    public abstract void displayBalance();

    public abstract void withdrawMoney(double withdrawAmount);
} // class BankAccountProduct

