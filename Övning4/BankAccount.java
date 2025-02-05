/**
 * Class representing a simple bank account.
 * It allows deposits, withdrawals, and balance checking.
 */
public class BankAccount {
    private double balance;

    /**
     * Creates a bank account with an initial balance.
     * @param initialBalance the starting balance
     */
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    /**
     * Deposits a certain amount into the account.
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws a certain amount from the account.
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if amount exceeds balance
     */
    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }

    /**
     * Returns the current balance.
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }
}