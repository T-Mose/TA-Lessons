public class CoinCounter {

    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;

    public CoinCounter() {
        this.pennies = 0;
        this.nickels = 0;
        this.dimes = 0;
        this.quarters = 0;
    }

    public void addCoins(int numPennies, int numNickels, int numDimes, int numQuarters) {
        pennies += numPennies;
        nickels += numNickels;
        dimes += numDimes;
        quarters += numQuarters;
    }

    public void addMultipleCoins(int times, int numPennies, int numNickels, int numDimes, int numQuarters) {
        for (int i = 0; i < times; i++) {
            addCoins(numPennies, numNickels, numDimes, numQuarters);
        }
    }

    public int getTotalValue() {
        int total = pennies;
        total += nickels * 5;
        total += dimes * 10;
        total += quarters * 25;
        return total;
    }

    public void printCoinSummary() {
        System.out.println("Pennies: " + pennies);
        System.out.println("Nickels: " + nickels);
        System.out.println("Dimes: " + dimes);
        System.out.println("Quarters: " + quarters);
        System.out.println("Total Value: " + getTotalValue() + " cents");
    }

    public static void main(String[] args) {
        CoinCounter counter = new CoinCounter();
        
        counter.addCoins(10, 5, 2, 1);
        counter.printCoinSummary();

        System.out.println("\nAdding multiple sets of coins...");
        counter.addMultipleCoins(3, 5, 1, 1, 0);
        counter.printCoinSummary();
    }
}