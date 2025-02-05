public class Loops {
    public static void main(String[] args) {
        int[] numbers = { 9, 2, 3, 4, 6 };

        // Loop1, i++ before teh array[i], leads to Index out of bounds runtime error
        try {
            doWhileLoop(numbers);
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        // Correct: For loop complicated but correct
        try {
            forLoop(numbers);
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        // Incorrect: While loop (Fails due to incorrect array indexing)
        try {
            whileLoop(numbers);
        } catch (Exception e) {
            System.out.println(e + "\n");
        }

        // Correct: For-Each loop
        try {
            forEachLoop(numbers);
        } catch (Exception e) {
            System.out.println(e + "\n");
        }
    }
    // Loop1
    public static void doWhileLoop(int[] array) {
        System.out.println("Do-While Loop:");
        int i = 0;
        int sum = 0;
        do {
            i++;
            sum += array[i];
        } while (i < array.length);
        System.out.println("Sum = " + sum + "\n");
    }
    // Loop2
    public static void forLoop(int[] array) {
        System.out.println("For Loop:");
        int sum = 0;
        for (int i = 0; i < array.length * 2; i += 2) {
            sum += array[i / 2];
        }
        System.out.println("Sum = " + sum + "\n");
    }
    // Loop3
    public static void whileLoop(int[] array) {
        System.out.println("While Loop:");
        int i = array.length;
        int sum = 0;
        while (i >= 0) {
            sum += array[array.length - i];
            i--;
        }
        System.out.println("Sum (up to error) = " + sum + "\n");
    }
    // Loop4
    public static void forEachLoop(int[] array) {
        System.out.println("For-Each Loop:");
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        System.out.println("Sum = " + sum + "\n");
    }
}
