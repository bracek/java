package sk.mka.books.patterns.factorymethod;

/**
 * The BankABC_AccountConcreteCreator is used to illustrate the Factory Method Pattern of GoF. The 
 * BankABC_AccountConcreteCreator is the concrete creator class that is defined through abstraction
 * BankAccountCreator. The BankABC_AccountConcreteCreator class creates specific BankAccountProduct
 * objects, depending on the type that is passed through the factory method. The created object is   
 * returned by the factory class through the BankAccountProduct abstraction.
 * 
 * @author Andre Mare
 */
public class BankABC_AccountConcreteCreator extends BankAccountCreator {

    @Override
    protected BankAccountProduct createBankAccount(String accountType) {
        if ("CHEQUE".equals(accountType)) {
            return new BankABC_ChequeAccountProduct();

        } else if ("SAVINGS".equals(accountType)) {
            return new BankABC_SavingsAccountProduct();

        } else {
            return null;
        }
    } // method createBankAccount
} // class BankABC_AccountConcreteCreator

