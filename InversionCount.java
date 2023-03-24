import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InversionCount {

    // Function to count the number of inversions during the merge process
    private static int mergeAndCount(int[] arr, int l, int m, int r) {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1); 

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        // Comparison part
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
                continue;
            }

            arr[k++] = right[j++];
            swaps += (m + 1) - (l + i);
        }

        // Copy remaining elements in left subarray
        while (i < left.length)
            arr[k++] = left[i++];
        
        // Copy remaining elements in left subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // The merge sort function responsible for the process of dividing the array
    private static int mergeSortAndCount(int[] arr, int l, int r) {

        // Keeps track of the inversion count at a particular node of the recursion tree
        int cnt = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count + right subarray count + merge count

            // Left subarray count
            cnt += mergeSortAndCount(arr, l, m);

            // Right subarray count
            cnt += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            cnt += mergeAndCount(arr, l, m, r);
        }

        return cnt;
    }

    // A convenience function that returns a boolean value for the puzzle's solvability
    private static boolean isSolvable(int[] conf) {
        return mergeSortAndCount(conf, 0, conf.length - 1) % 2 == 0;
    }

    // A utility function for checking duplicate integers in an instance of a puzzle
    public static boolean hasDuplicates(String str) {
        Map<String, Long> map = Arrays.stream(str.split(""))
            .collect(Collectors.groupingBy(Function.identity(), 
                    Collectors.counting()));
        return map.values().stream().anyMatch(count -> count >= 2);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num;
        
        // Error handling
        try {
            num = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scan.close();
            return;
        }

        // Exit program immediately if number of test cases is negative
        if (num < 0) {
            System.out.println("Only nonnegative integers allowed.");
            scan.close();
            return;
        }

        scan.nextLine();
        String configs[] = new String[num];

        // Take configurations as input and check for any invalid inputs
        for(int i = 0; i < num; i++) {
            configs[i] = scan.nextLine();

            if (configs[i].length() != 9 || !configs[i].matches("\\d*-\\d*")) {
                System.out.println("Invalid input.");
                scan.close();
                return;
            }

            if (hasDuplicates(configs[i])) {
                System.out.println("Repeating integers are not allowed.");
                scan.close();
                return;
            }
        }

        System.out.println();
        scan.close();

        // Convert the String arrays taken from input into integer arrays before
        // computing for solvability
        for (String config : configs) {
            String[] chars = config.split("");
            int[] conf = new int[config.length() - 1];

            int idx = 0;
            for (int i = 0; i < chars.length; i++) {
                try {
                    conf[idx] = Integer.parseInt(chars[i]);
                    idx++;
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }

            System.out.println(isSolvable(conf) ? "Solvable." : "Not Solvable.");
        }
    }
}
