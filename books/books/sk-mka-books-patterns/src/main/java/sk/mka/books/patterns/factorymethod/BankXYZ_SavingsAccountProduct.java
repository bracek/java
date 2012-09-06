package sk.mka.books.patterns.factorymethod;

/**
 * The BankXYZ_SavingsAccountProduct is used to illustrate the Factory Method Pattern of GoF. The 
 * BankXYZ_SavingsAccountProduct is the concrete product class that is defined through abstraction
 * BankAccountProduct. The BankXYZ_SavingsAccountProduct class will be created by the factory method
 * and the instance of the class will be returned by the factory class through the BankAccountProduct
 * abstraction.
 * 
 * @author Andre Mare
 */
public class BankXYZ_SavingsAccountProduct extends BankAccountProduct {

    private double accountBalance = 0.00;

    public BankXYZ_SavingsAccountProduct() {
        System.out.println("Bank XYZ - Savings Account : Creating account.");
    } // no-arg constructor

    public void depositMoney(double depositAmount) {
        accountBalance += depositAmount;
        System.out.println("Bank XYZ - Savings Account : Deposit money " + depositAmount);
    } // method depositMoney

    public void displayBalance() {
        System.out.println("Bank XYZ - Savings Account : Acount Balance " + accountBalance);
    } // method displayBalance

    public void withdrawMoney(double withdrawAmount) {
        accountBalance -= withdrawAmount;
        System.out.println("Bank XYZ - Savings Account : Withdraw money " + withdrawAmount);
    } // method withdrawMoney
} // class BankXYZ_SavingsAccountProduct

