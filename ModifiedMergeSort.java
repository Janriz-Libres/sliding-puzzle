import java.util.Arrays;

public class ModifiedMergeSort {

    // Function to count the number of inversions
    // during the merge process
    private static int mergeAndCount(int[] arr, int l, int m, int r) {

        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        while (i < left.length)
            arr[k++] = left[i++];
            
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r) {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    private static boolean isSolvable(int[] arr) {
        int cnt = mergeSortAndCount(arr, 0, arr.length - 1);
        System.out.println("\nInversion count: " + cnt);
        return cnt % 2 == 0;
    }

    public static void main(String[] args) {
        String[] configs = {
            "12364-785", // 12346-785   1 diff
                         // 123456-78   3 diffs
                         // 1234567-8   
                         // 12345678-   
            "12368457-", // 12346857-   2 diffs
                         // 12345687-   2 diffs
                         // 12345678-   1 diff
            "15-328467", // 125-38467   2 diffs
                         // 1235-8467   1 diff
                         // 12345-867   2 diffs
                         // 123456-87   1 diff
                         // 1234567-8   1 diffs
                         // 12345678- 
            "57814623-",
            "357248-61"
        };

        for (String config : configs) {
            String[] chars = config.split("");
            int[] equiv = new int[config.length() - 1];

            int idx = 0;
            for (int i = 0; i < chars.length; i++) {
                try {
                    equiv[idx] = Integer.parseInt(chars[i]);
                    idx++;
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }

            System.out.println(isSolvable(equiv) ? "Solvable." : "Not Solvable.");
        }
    }
}
