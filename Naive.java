public class Sliding {

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
            "15328467",  // 2+1+2+1+1
            "57814623-",
            "357248-61"
        };

        for (String config : configs)
            System.out.println(isSolvable(config) ? "Solvable." : "Not Solvable.");
    }

    static boolean isSolvable(String config) {
        int inaptPairs = 0;

        for (int i = 0; i < config.length() - 1; i++) {
            String s1 = String.valueOf(config.charAt(i));
            
            for (int j = i + 1; j < config.length(); j++) {
                String s2 = String.valueOf(config.charAt(j));

                try {
                    if (Integer.parseInt(s1) > Integer.parseInt(s2))
                        inaptPairs++;
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }
        }

        System.out.println(inaptPairs);
        return inaptPairs % 2 == 0;
    }
}