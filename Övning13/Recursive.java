public class Recursive {

    // Easy: Sum of an Array
    public static int sumArray(int[] arr, int index) {
        if (index == arr.length) return 0;
        return arr[index] + sumArray(arr, index + 1);
    }

    // Easy: Factorial
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    // Medium: Reverse a String
    public static String reverseString(String s) {
        if (s.isEmpty()) return s;
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    // Medium: Find the Maximum in an Array
    public static int findMax(int[] arr, int index, int max) {
        if (index == arr.length) return max;
        return findMax(arr, index + 1, Math.max(max, arr[index]));
    }

    // Medium: Binary Search
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) return binarySearch(arr, target, mid + 1, right);
        return binarySearch(arr, target, left, mid - 1);
    }

    // Hard: Fibonacci Sequence
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Hard: String Palindrome Check
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        // Test examples
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Sum of array: " + sumArray(arr, 0));
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Reverse of 'hello': " + reverseString("hello"));
        System.out.println("Max in array: " + findMax(arr, 0, Integer.MIN_VALUE));
        System.out.println("Binary search for 3: " + binarySearch(arr, 3, 0, arr.length - 1));
        System.out.println("Fibonacci of 6: " + fibonacci(6));
        System.out.println("Is 'racecar' a palindrome? " + isPalindrome("racecar"));
    }
}
