public class VendingMachine {

    private Product[] products;
    private int[] quantities;
    private static final int MAX_PRODUCTS = 10;
    private int productCount;

    public VendingMachine() {
        this.products = new Product[MAX_PRODUCTS];
        this.quantities = new int[MAX_PRODUCTS];
        this.productCount = 0;
    }

    public boolean addProduct(String name, double price, int quantity) {
        if (productCount < MAX_PRODUCTS) {
            products[productCount] = new Product(name, price);
            quantities[productCount] = quantity;
            productCount++;
            return true;
        }
        return false; // Vending machine is full
    }

    public double buyProduct(String name, double payment) {
        int productIndex = findProductIndex(name);
        if (productIndex != -1 && quantities[productIndex] > 0) {
            Product product = products[productIndex];
            if (payment >= product.getPrice()) {
                quantities[productIndex]--;
                return payment - product.getPrice(); // Return change
            }
        }
        return -1; // Purchase failed
    }

    public boolean restockProduct(String name, int quantity) {
        int productIndex = findProductIndex(name);
        if (productIndex != -1) {
            quantities[productIndex] += quantity;
            return true;
        }
        return false;
    }

    public boolean isProductAvailable(String name) {
        int productIndex = findProductIndex(name);
        return productIndex != -1 && quantities[productIndex] > 0;
    }

    public void printProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < productCount; i++) {
            if (quantities[i] > 0) {
                System.out.println(products[i] + " - Quantity: " + quantities[i]);
            }
        }
    }

    private int findProductIndex(String name) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inner class representing a product.
     */
    class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ")";
        }
    }

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.addProduct("Soda", 1.50, 10);
        machine.addProduct("Chips", 1.00, 5);
        machine.addProduct("Candy", 0.75, 20);

        machine.printProducts();

        System.out.println("\nBuying 'Soda' with $2.00: Change = $" + machine.buyProduct("Soda", 2.00));
        machine.printProducts();

        System.out.println("\nRestocking 'Chips' with 3 more units.");
        machine.restockProduct("Chips", 3);
        machine.printProducts();

        System.out.println("\nIs 'Candy' available? " + machine.isProductAvailable("Candy"));
    }
}
