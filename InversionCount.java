import java.util.Arrays;
import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        scan.nextLine();
        String configs[] = new String[num];

        // Take configurations as input
        for(int i = 0; i < num; i++)
            configs[i] = scan.nextLine();

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
