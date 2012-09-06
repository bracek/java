package sk.mka.books.patterns.factorymethod;

public class BankSystemClient {

    public static void main(String[] args) {
        BankAccountCreator bankSystemCreator = new BankXYZ_AccountConcreteCreator();
        BankAccountProduct account = bankSystemCreator.createBankAccount("CHEQUE");

        account.depositMoney(10.00);
        account.displayBalance();
        account.withdrawMoney(5.00);
        account.displayBalance();
    }
}
