package sk.mka.books.patterns.factorymethod;

/**
 * The BankXYZ_ChequeAccountProduct is used to illustrate the Factory Method Pattern of GoF. The 
 * BankXYZ_ChequeAccountProduct is the concrete product class that is defined through abstraction
 * BankAccountProduct. The BankXYZ_ChequeAccountProduct class will be created by the factory method
 * and the instance of the class will be returned by the factory class through the BankAccountProduct
 * abstraction.
 * 
 * @author Andre Mare
 */
public class BankXYZ_ChequeAccountProduct extends BankAccountProduct {

    private double accountBalance = 0.00;

    public BankXYZ_ChequeAccountProduct() {
        System.out.println("Bank XYZ - Cheque Account : Creating account.");
    } // no-arg constructor

    @Override
    public void depositMoney(double depositAmount) {
        accountBalance += depositAmount;
        System.out.println("Bank XYZ - Cheque Account : Deposit money " + depositAmount);
    } // method depositMoney

    @Override
    public void displayBalance() {
        System.out.println("Bank XYZ - Cheque Account : Acount Balance " + accountBalance);
    } // method displayBalance

    @Override
    public void withdrawMoney(double withdrawAmount) {
        accountBalance -= withdrawAmount;
        System.out.println("Bank XYZ - Cheque Account : Withdraw money " + withdrawAmount);
    } // method withdrawMoney
} // class BankXYZ_ChequeAccountProduct

