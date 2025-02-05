public class BankSystem {
    public static void main(String[] args) {
        // Customer 1: Alice (regular account)
        String customer1Name = "Alice";
        double customer1Balance = 1000.0;
        System.out.println(customer1Name + "'s starting balance: " + customer1Balance);

        // Customer 2: Bob (regular account)
        String customer2Name = "Bob";
        double customer2Balance = 1500.0;
        System.out.println(customer2Name + "'s starting balance: " + customer2Balance);

        // Customer 3: Charlie (savings account with $500 minimum balance)
        String customer3Name = "Charlie";
        double customer3Balance = 2000.0;
        double customer3MinBalance = 500.0;
        System.out.println(customer3Name + "'s starting balance: " + customer3Balance + "\n");
        
        // Deposit for Alice
        double deposit1 = 500.0;
        customer1Balance += deposit1;
        System.out.println(customer1Name + " deposits " + deposit1 + ". New balance: " + customer1Balance);

        // Withdrawal for Bob
        double withdrawal1 = 300.0;
        if (customer2Balance >= withdrawal1) {
            customer2Balance -= withdrawal1;
            System.out.println(customer2Name + " withdraws " + withdrawal1 + ". New balance: " + customer2Balance);
        } else {
            System.out.println(customer2Name + " has insufficient funds.");
        }

        // Transfer from Alice to Bob
        double transfer1 = 200.0;
        if (customer1Balance >= transfer1) {
            customer1Balance -= transfer1;
            customer2Balance += transfer1;
            System.out.println(customer1Name + " transfers " + transfer1 + " to " + customer2Name + ".");
            System.out.println(customer1Name + "'s new balance: " + customer1Balance);
            System.out.println(customer2Name + "'s new balance: " + customer2Balance);
        } else {
            System.out.println(customer1Name + " has insufficient funds to transfer.");
        }

        // Withdrawal for Charlie (check minimum balance)
        double withdrawal2 = 1200.0;
        if (customer3Balance - withdrawal2 >= customer3MinBalance) {
            customer3Balance -= withdrawal2;
            System.out.println(customer3Name + " withdraws " + withdrawal2 + ". New balance: " + customer3Balance);
        } else {
            System.out.println(customer3Name + " cannot withdraw " + withdrawal2 + " due to minimum balance restriction.");
        }

        // Transfer from Charlie to Bob (check minimum balance)
        double transfer2 = 400.0;
        if (customer3Balance - transfer2 >= customer3MinBalance) {
            customer3Balance -= transfer2;
            customer2Balance += transfer2;
            System.out.println(customer3Name + " transfers " + transfer2 + " to " + customer2Name + ".");
            System.out.println(customer3Name + "'s new balance: " + customer3Balance);
            System.out.println(customer2Name + "'s new balance: " + customer2Balance);
        } else {
            System.out.println(customer3Name + " cannot transfer " + transfer2 + " due to minimum balance restriction.");
        }

        // Final balance display
        System.out.println("\nFinal balances:");
        System.out.println(customer1Name + "'s final balance: " + customer1Balance);
        System.out.println(customer2Name + "'s final balance: " + customer2Balance);
        System.out.println(customer3Name + "'s final balance: " + customer3Balance);
    }
}
