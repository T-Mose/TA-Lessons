// Skapa en ny fil med namn Recursive.java och omimplementera dessa funktioner (välj svårighetsgrad) rekursivt

public class Iterative {

    // Easy: Sum of an Array
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // Easy: Factorial
    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Medium: Reverse a String
    public static String reverseString(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }
        return reversed.toString();
    }

    // Medium: Find the Maximum in an Array
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Medium: Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Hard: Fibonacci Sequence
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // Hard: String Palindrome Check
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test examples
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Sum of array: " + sumArray(arr));
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Reverse of 'hello': " + reverseString("hello"));
        System.out.println("Max in array: " + findMax(arr));
        System.out.println("Binary search for 3: " + binarySearch(arr, 3));
        System.out.println("Fibonacci of 6: " + fibonacci(6));
        System.out.println("Is 'racecar' a palindrome? " + isPalindrome("racecar"));
    }
}