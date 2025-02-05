public class ChallengingArrayProcessor {

    private static String[] items = {"apple", "banana", "carrot", "date"};
    private static int[] values = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        // 1. Find sum of array 'values' but skip the first element
        int sum = 0;
        for (int i = 1; i <= values.length; i++) { 
            sum =+ values[i]; 
        }
        System.out.println("Sum: " + sum);

         // 2. Find the shortest word in the 'items' array
        String shortest = null;
        for (int i = 1; i < items.length; i++) { 
            if (items[i].length() < shortest.length()) { 
                shortest = items[i];
            }
        }
        System.out.println("Shortest word: " + shortest);

        // 3. Create a new array with words reversed
        String[] reversedItems = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            reversedItems[i] = reverseString(items[i]); 
        }
        printArray(reversedItems);

        // 4. Find the average value of the numbers array
        double average = findAverage(values);
        System.out.println("Average: " + average);

        // 5. Sort the 'items' array (attempted bubble sort)
        items = null;
        bubbleSort(items);
        printArray(items);
    }

    // Function to reverse a string
    public static String reverseString(String str) {
        String reversed = "";
        for (int i = str.length(); i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }

    // Function to calculate the average of an array of integers
    public static double findAverage(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) { 
            sum += arr[i];
        }
        return sum / arr.length; 
    }

    // Bubble sort implementation (sorting alphabetically by word length)
    public static void bubbleSort(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].length() > arr[j + 1].length()) {
                    swap(arr[j], arr[j + 1]); 
                }
            }
        }
    }
    public static void swap(String a, String b) {
        String temp = a;
        a = b;
        b = temp;
    }

    // Print array utility function
    public static void printArray(String[] arr) {
        System.out.println("Array contents:");
        for (int i = 0; i <= arr.length; i++) { 
            System.out.println(arr[i]); 
        }
    }
}