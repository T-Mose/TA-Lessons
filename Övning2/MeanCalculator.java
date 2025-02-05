public class MeanCalculator {

    // mean1: uses integer division, may result in incorrect mean
    public static double mean1(int[] d, int len) {
        int i, sum = 0;
        for (i = 0; i < len; i++) {
            sum += d[i];
        }
        return sum / len;  // This is integer division
    }

    // mean2: stores sum in double, still uses integer division for result
    public static double mean2(int[] d, int len) {
        int i;
        double sum = 0;
        for (i = 0; i < len; i++) {
            sum += d[i];
        }
        return sum / len;  // Potentially correct, but let's see the result
    }

    // mean3: casts sum to double before division
    public static double mean3(int[] d, int len) {
        int i, sum = 0;
        for (i = 0; i < len; i++) {
            sum += d[i];
        }
        return (double) sum / len;  // Correct result
    }

    // mean4: casts sum to double before returning
    public static double mean4(int[] d, int len) {
        int i, sum = 0;
        for (i = 0; i < len; i++) {
            sum += d[i];
        }
        return (double) (sum / len);  // Incorrect due to integer division happening before cast
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 3};  // Sample array
        int len = arr.length;

        // Test each method
        System.out.println("mean1 result: " + mean1(arr, len));  // Incorrect due to integer division
        System.out.println("mean2 result: " + mean2(arr, len));  // May still not be completely correct
        System.out.println("mean3 result: " + mean3(arr, len));  // Correct mean
        System.out.println("mean4 result: " + mean4(arr, len));  // Incorrect due to cast after division
    }
}
