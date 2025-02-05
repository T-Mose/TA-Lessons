class BankAccount {
    protected String name;
    protected double balance;

    public BankAccount(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(name + " deposits " + amount + ". New balance: " + this.balance);
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println(name + " withdraws " + amount + ". New balance: " + this.balance);
        } else {
            System.out.println(name + " has insufficient funds.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            recipient.balance += amount;
            System.out.println(this.name + " transfers " + amount + " to " + recipient.name + ".");
            System.out.println(this.name + "'s new balance: " + this.balance);
            System.out.println(recipient.name + "'s new balance: " + recipient.balance);
        } else {
            System.out.println(this.name + " has insufficient funds to transfer.");
        }
    }

    public void printAccountInfo() {
        System.out.println("Account Holder: " + name + ", Balance: " + balance);
    }
}

// SavingsAccount inherits from BankAccount
class SavingsAccount extends BankAccount {
    private double minBalance;

    public SavingsAccount(String name, double initialBalance, double minBalance) {
        super(name, initialBalance);
        this.minBalance = minBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance - amount >= minBalance) {
            this.balance -= amount;
            System.out.println(name + " withdraws " + amount + ". New balance: " + this.balance);
        } else {
            System.out.println(name + " cannot withdraw " + amount + " due to minimum balance restriction.");
        }
    }

    @Override
    public void transfer(BankAccount recipient, double amount) {
        if (this.balance - amount >= minBalance) {
            this.balance -= amount;
            recipient.balance += amount;
            System.out.println(this.name + " transfers " + amount + " to " + recipient.name + ".");
            System.out.println(this.name + "'s new balance: " + this.balance);
            System.out.println(recipient.name + "'s new balance: " + recipient.balance);
        } else {
            System.out.println(this.name + " cannot transfer " + amount + " due to minimum balance restriction.");
        }
    }
}

public class BankSystemOO {
    public static void main(String[] args) {
        // Regular accounts for Alice and Bob
        BankAccount alice = new BankAccount("Alice", 1000.0);
        BankAccount bob = new BankAccount("Bob", 1500.0);

        // Savings account for Charlie
        SavingsAccount charlie = new SavingsAccount("Charlie", 2000.0, 500.0);

        // Perform operations
        alice.deposit(500.0);
        bob.withdraw(300.0);
        alice.transfer(bob, 200.0);

        charlie.withdraw(1200.0);  // Should fail due to minimum balance restriction
        charlie.transfer(bob, 400.0);  // Should fail due to minimum balance restriction

        // Print final balances
        alice.printAccountInfo();
        bob.printAccountInfo();
        charlie.printAccountInfo();
    }
}
